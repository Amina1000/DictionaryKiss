package com.cocos.develop.dictionarykiss.ui.description

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.dictionarykiss.R
import com.cocos.develop.dictionarykiss.databinding.ActivityDescriptionBinding
import com.cocos.develop.dictionarykiss.di.injectDependencies
import com.cocos.develop.dictionarykiss.utils.stopRefreshAnimationIfNeeded
import com.cocos.develop.dictionarykiss.utils.usePicassoToLoadPhoto
import com.cocos.develop.model.data.DataModel
import com.cocos.develop.utils.network.OnlineLiveData
import com.cocos.develop.utils.ui.AlertDialogFragment
import kotlinx.android.synthetic.main.activity_description.*
import org.koin.android.scope.currentScope

class DescriptionActivity : AppCompatActivity() {

    private val binding: ActivityDescriptionBinding by viewBinding(ActivityDescriptionBinding::bind)
    private lateinit var model: DescriptionViewModel
    private var data: DataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        setActionbarHomeButtonAsUp()
        binding.descriptionScreenSwipeRefreshLayout.setOnRefreshListener { startLoadingOrShowError() }
        setData()
        iniViewModel()
        initView()
    }

    private fun initView() {
        data?.let{dataModel->
            favorite_fab.setOnClickListener {
                if (dataModel.favorite){
                    dataModel.favorite = false
                    setFavoriteImageFab(dataModel.favorite)
                    model.setData(dataModel)
                }else{
                    dataModel.favorite = true
                    setFavoriteImageFab(dataModel.favorite)
                    model.setData(dataModel)
                }
            }
        }

    }

    private fun setFavoriteImageFab(favorite:Boolean){
        if (favorite){
            favorite_fab.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            favorite_fab.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun iniViewModel() {
        injectDependencies()
        val viewModel: DescriptionViewModel by currentScope.inject()
        model = viewModel

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
        binding.descriptionHeader.text =  bundle?.getString(WORD_EXTRA)
        binding.descriptionTextView.text = bundle?.getString(DESCRIPTION_EXTRA)
        binding.transcriptionWord.text = bundle?.getString(TRANSCRIPTION)
        binding.soundUrl.text = bundle?.getString(SOUND_URL)

        val imageLink = bundle?.getString(URL_EXTRA)
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
        private const val WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b0"
        private const val DESCRIPTION_EXTRA = "0eeb92aa-520b-4fd1-bb4b-027fbf963d9a"
        private const val URL_EXTRA = "6e4b154d-e01f-4953-a404-639fb3bf7281"
        private const val TRANSCRIPTION = "5e4b154d-e01f-4953-a404-639fb3bf7281"
        private const val SOUND_URL = "8e4b154d-e01f-4953-a404-639fb3bf7281"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            url: String?,
            transcription: String?,
            soundUrl: String?

        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(WORD_EXTRA, word)
            putExtra(DESCRIPTION_EXTRA, description)
            putExtra(URL_EXTRA, url)
            putExtra(TRANSCRIPTION, transcription)
            putExtra(SOUND_URL, soundUrl)
        }
    }
}
