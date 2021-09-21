package com.cocos.develop.dictionarykiss.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cocos.develop.dictionarykiss.R
import com.cocos.develop.core.base.BaseActivity
import com.cocos.develop.dictionarykiss.di.injectDependencies
import com.cocos.develop.dictionarykiss.ui.description.DescriptionActivity
import com.cocos.develop.dictionarykiss.ui.main.*
import com.cocos.develop.dictionarykiss.utils.convertMeaningsToString
import com.cocos.develop.model.data.AppState
import com.cocos.develop.utils.network.isOnline
import com.cocos.develop.utils.ui.viewById
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

private const val HISTORY_ACTIVITY_PATH = "com.cocos.develop.favoritescreen.ui.FavoriteActivity"
private const val HISTORY_ACTIVITY_FEATURE_NAME = "favoriteScreen"

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
            override fun onItemClick(data: com.cocos.develop.model.data.DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings!![0].imageUrl,
                        data.meanings!![0].transcription,
                        data.meanings!![0].soundUrl
                    )
                )
            }
        }
    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniViewModel()
        initViews()
    }

    private fun iniViewModel() {
        check(main_activity_recyclerview.adapter == null) {getString(R.string.error_initialised)}
        injectDependencies()
        val viewModel: MainViewModel by currentScope.inject()
        model = viewModel
        model.getData("", false)
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        searchFAB.setOnClickListener(fabClickListener)
        mainRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        mainRecyclerview.adapter = adapter

        val itemDecoration =DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.separator_vertical, null)!!
        )
        mainRecyclerview.addItemDecoration(itemDecoration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                splitInstallManager = SplitInstallManagerFactory.create(applicationContext)
                val request =
                    SplitInstallRequest
                        .newBuilder()
                        .addModule(HISTORY_ACTIVITY_FEATURE_NAME)
                        .build()

                splitInstallManager
                    .startInstall(request)
                    .addOnSuccessListener {
                        val intent = Intent().setClassName(packageName, HISTORY_ACTIVITY_PATH)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            applicationContext,
                            "Couldn't download feature: " + it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

    override fun setDataToAdapter(data: List<com.cocos.develop.model.data.DataModel>) {
        adapter.setData(data)
    }
}
