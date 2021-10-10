package com.cocos.develop.dictionarykiss.ui.description

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.core.base.BaseActivity
import com.cocos.develop.dictionarykiss.R
import com.cocos.develop.dictionarykiss.databinding.ActivityDescriptionBinding
import com.cocos.develop.dictionarykiss.di.injectDependencies
import com.cocos.develop.dictionarykiss.utils.convertMeaningsToString
import com.cocos.develop.dictionarykiss.utils.stopRefreshAnimationIfNeeded
import com.cocos.develop.dictionarykiss.utils.usePicassoToLoadPhoto
import com.cocos.develop.model.data.AppState
import com.cocos.develop.model.data.DataModel
import com.cocos.develop.utils.network.OnlineLiveData
import com.cocos.develop.utils.ui.AlertDialogFragment
import kotlinx.android.synthetic.main.activity_description.*
import org.koin.android.scope.currentScope

class DescriptionActivity : BaseActivity<AppState, DescriptionInteractor>() {

    private val binding: ActivityDescriptionBinding by viewBinding(ActivityDescriptionBinding::bind)
    override lateinit var model: DescriptionViewModel
    private var dataEntity: DataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        setActionbarHomeButtonAsUp()
        binding.descriptionScreenSwipeRefreshLayout.setOnRefreshListener { startLoadingOrShowError() }
        setData()
        iniViewModel()

        dataEntity?.let{dataModel->
            favorite_fab.setOnClickListener {
                dataModel.favorite = !dataModel.favorite
                setFavoriteImageFab(dataModel.favorite)
                model.setData(dataModel)
            }}
    }

    private fun setFavoriteImageFab(favorite: Boolean) {
        if (favorite) {
            favorite_fab.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            favorite_fab.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun iniViewModel() {
        injectDependencies()
        val viewModel: DescriptionViewModel by currentScope.inject()
        model = viewModel
        model.getData("", false)
        model.subscribe().observe(this@DescriptionActivity, { renderData(it) })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setData() {

        val bundle = intent.extras
        dataEntity = bundle?.getParcelable(DATA_MODEL)
        dataEntity?.let {
            binding.descriptionHeader.text = it.text
            it.meanings?.also { listMeanings ->
                binding.descriptionTextView.text = convertMeaningsToString(listMeanings)
                binding.transcriptionWord.text = listMeanings.first().transcription
                binding.soundUrl.text = String.format("https:%s", listMeanings.first().soundUrl)

                val imageLink = listMeanings.first().imageUrl
                if (imageLink.isNullOrBlank()) {
                    stopRefreshAnimationIfNeeded(
                        binding.descriptionScreenSwipeRefreshLayout
                    )
                } else {
                    binding.descriptionImageview.usePicassoToLoadPhoto(
                        imageLink,
                        binding.descriptionScreenSwipeRefreshLayout
                    )
                    //description_imageview.useGlideToLoadPhoto(imageLink,description_screen_swipe_refresh_layout)
                }
            }
        }
    }

    private fun startLoadingOrShowError() {
        OnlineLiveData(this).observe(
            this@DescriptionActivity,
            {
                if (it) {
                    setData()
                } else {
                    AlertDialogFragment.newInstance(
                        getString(R.string.dialog_title_device_is_offline),
                        getString(R.string.dialog_message_device_is_offline)
                    ).show(
                        supportFragmentManager,
                        DIALOG_FRAGMENT_TAG
                    )
                    stopRefreshAnimationIfNeeded(binding.descriptionScreenSwipeRefreshLayout)
                }
            })
    }

    companion object {

        private const val DIALOG_FRAGMENT_TAG = "8c7dff51-9769-4f6d-bbee-a3896085e76e"
        private const val DATA_MODEL = "e76a288a-5dcc-43f1-ba89-7fe1d53f63b8"

        fun getIntent(
            context: Context,
            dataModel: DataModel?

        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(DATA_MODEL, dataModel)
        }
    }

    override fun setDataToScreen(data: List<DataModel>) {
        data.forEach {
            if (it.id == dataEntity?.id) {
                dataEntity?.favorite = it.favorite
            }
       }
        dataEntity?.let{dataModel->
            setFavoriteImageFab(dataModel.favorite)
            model.setData(dataModel)
        }

    }

}
