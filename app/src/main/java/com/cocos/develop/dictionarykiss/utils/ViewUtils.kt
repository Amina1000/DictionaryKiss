package com.cocos.develop.dictionarykiss.utils

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cocos.develop.dictionarykiss.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_description.*

/**
 * homework com.cocos.develop.dictionarykiss.utils
 *
 * @author Amina
 * 27.08.2021
 */

//extension-функция, обработка изображения
fun AppCompatImageView.loadImageFromResource(imageUrl: String?) {
    imageUrl?.let {
        Picasso
            .get()
            .load("https:$it")
            .placeholder(R.drawable.ic_no_photo_vector).fit().centerCrop()
            .into(this)
    }

}


fun ImageView.usePicassoToLoadPhoto(imageLink: String,view:SwipeRefreshLayout) {
    Picasso.get().load("https:$imageLink")
        .placeholder(R.drawable.ic_no_photo_vector).fit().centerCrop()
        .into( this@usePicassoToLoadPhoto,object : Callback {
            override fun onSuccess() {
                stopRefreshAnimationIfNeeded(view)
            }

            override fun onError(e: Exception?) {
                stopRefreshAnimationIfNeeded(view)
                this@usePicassoToLoadPhoto.setImageResource(R.drawable.ic_load_error_vector)
            }
        })
}

fun stopRefreshAnimationIfNeeded(view:SwipeRefreshLayout) {
    if (view.isRefreshing) {
        view.isRefreshing = false
    }
}