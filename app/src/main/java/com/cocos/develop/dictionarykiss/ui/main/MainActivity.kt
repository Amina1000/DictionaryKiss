package com.cocos.develop.dictionarykiss.ui.main

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cocos.develop.core.base.BaseActivity
import com.cocos.develop.dictionarykiss.R
import com.cocos.develop.dictionarykiss.di.injectDependencies
import com.cocos.develop.dictionarykiss.ui.description.DescriptionActivity
import com.cocos.develop.dictionarykiss.ui.history.HistoryActivity
import com.cocos.develop.dictionarykiss.ui.main.*
import com.cocos.develop.model.data.AppState
import com.cocos.develop.model.data.DataModel
import com.cocos.develop.utils.ui.viewById
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope

private const val SLIDE_LEFT_DURATION = 1000L
private const val COUNTDOWN_DURATION = 2000L
private const val COUNTDOWN_INTERVAL = 1000L
private const val FAVORITE_ACTIVITY_PATH = "com.cocos.develop.favoritescreen.ui.FavoriteActivity"
private const val FAVORITE_ACTIVITY_FEATURE_NAME = "favoriteScreen"
private const val REQUEST_CODE = 38
private const val FAVORITE_SCREEN_TAG = "install favorite screen"

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    override lateinit var model: MainViewModel
    private lateinit var splitInstallManager: SplitInstallManager

    //Объявляем переменные на уровне класса
    private val mainRecyclerview by viewById<RecyclerView>(R.id.main_activity_recyclerview)
    private val searchFAB by viewById<FloatingActionButton>(R.id.search_fab)

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data
                    )
                )
            }
        }
    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    // Объявим переменную - она пригодится в дальнейшем
    private lateinit var appUpdateManager: AppUpdateManager
    private val stateUpdatedListener: InstallStateUpdatedListener =
        InstallStateUpdatedListener { p0 ->
            if (p0.installStatus() == InstallStatus.DOWNLOADED) {
                // Когда обновление скачалось и готово к установке, отображаем
                // SnackBar
                popupSnackbarForCompleteUpdate()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDefaultSplashScreen()
        iniViewModel()
        initViews()
        checkForUpdates()
        installFavoriteScreen()
    }

    // Сам метод вызываем в onCreate
    private fun checkForUpdates() {
        // Создаём менеджер
        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        // Возвращает интент (appUpdateInfo), который мы будем использовать
        // в качестве информации для обновления
        val appUpdateInfo = appUpdateManager.appUpdateInfo

        // Проверяем наличие обновления
        appUpdateInfo.addOnSuccessListener { appUpdateIntent ->
            if (appUpdateIntent.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                // Здесь мы делаем проверку на немедленный тип обновления
                // (IMMEDIATE); для гибкого нужно передавать AppUpdateType.FLEXIBLE
                && appUpdateIntent.isUpdateTypeAllowed(IMMEDIATE)
            ) {
                // Передаём слушатель прогресса (только для гибкого типа
                // обновления)
                appUpdateManager.registerListener(stateUpdatedListener)
                // Выполняем запрос
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateIntent,
                    IMMEDIATE,
                    this,
                    // Реквест-код для обработки запроса в onActivityResult
                    REQUEST_CODE
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Если всё в порядке, снимаем слушатель прогресса обновления
                appUpdateManager.unregisterListener(stateUpdatedListener)
            } else {
                // Если обновление прервано (пользователь не принял или прервал
                // его) или не загружено (из-за проблем с соединением), показываем
                // уведомление (также можно показать диалоговое окно с предложением
                // попробовать обновить еще раз)
                Toast.makeText(
                    applicationContext,
                    "Update flow failed! Result code: $resultCode",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun popupSnackbarForCompleteUpdate() {
        Snackbar.make(
            findViewById(R.id.activity_main_layout),
            "An update has just been downloaded.",
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction("RESTART") { appUpdateManager.completeUpdate() }
            show()
        }
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager
            .appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                // Обновление скачано, но не установлено
                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                    popupSnackbarForCompleteUpdate()
                }
                if (appUpdateInfo.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                ) {
                    // Обновление прервано - можно возобновить установку
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        IMMEDIATE,
                        this,
                        REQUEST_CODE
                    )
                }
            }

    }

    private fun setDefaultSplashScreen() {
        // пока скроем анимацию
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            setSplashScreenHideAnimation()
//        }

        setSplashScreenDuration()
    }

    @RequiresApi(31)
    private fun setSplashScreenHideAnimation() {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideLeft = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.height.toFloat()
            )
            slideLeft.interpolator = AnticipateInterpolator()
            slideLeft.duration = SLIDE_LEFT_DURATION

            slideLeft.doOnEnd { splashScreenView.remove() }
            slideLeft.start()
        }
    }

    private fun setSplashScreenDuration() {
        var isHideSplashScreen = false

        object : CountDownTimer(COUNTDOWN_DURATION, COUNTDOWN_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                isHideSplashScreen = true
            }
        }.start()

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isHideSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    private fun iniViewModel() {
        check(main_activity_recyclerview.adapter == null) { getString(R.string.error_initialised) }
        injectDependencies()
        val viewModel: MainViewModel by currentScope.inject()
        model = viewModel
        model.getData("", false)
        model.subscribe().observe(this@MainActivity, {
            renderData(it)
            empty_linear_layout.isVisible = emptyLayout})
    }

    private fun initViews() {
        searchFAB.setOnClickListener(fabClickListener)
        mainRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        mainRecyclerview.adapter = adapter

        val itemDecoration = DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.separator_vertical, null)!!
        )
        mainRecyclerview.addItemDecoration(itemDecoration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                isInstalled()
                true
            }
            R.id.menu_history -> {
                val intent = Intent(this, HistoryActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun isInstalled() {
        if (splitInstallManager.installedModules.contains(FAVORITE_ACTIVITY_FEATURE_NAME)) {
            try {
                val classFavorite = Class.forName(FAVORITE_ACTIVITY_PATH, false, javaClass.classLoader)
                val intent = Intent(this, classFavorite)
                startActivity(intent)

            } catch (ignored: ClassNotFoundException) {
                ignored.message?.let { Log.e(FAVORITE_SCREEN_TAG, it) }
            }
        }
    }

    private fun installFavoriteScreen() {

        splitInstallManager = SplitInstallManagerFactory.create(applicationContext)
        val request =
            SplitInstallRequest
                .newBuilder()
                .addModule(FAVORITE_ACTIVITY_FEATURE_NAME)
                .build()

        splitInstallManager
            .startInstall(request)
            .addOnSuccessListener {
                Log.d(FAVORITE_SCREEN_TAG, it.toString())
            }

            .addOnFailureListener {
                Log.e(FAVORITE_SCREEN_TAG, it.toString())
            }
    }


    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

    override fun setDataToScreen(data: List<DataModel>) {
        adapter.setData(data)
    }
}
