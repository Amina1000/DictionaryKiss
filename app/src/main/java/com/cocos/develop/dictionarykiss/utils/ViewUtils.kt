package com.cocos.develop.dictionarykiss.utils


import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.cocos.develop.dictionarykiss.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_description.*
import java.lang.Exception

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

fun ImageView.useGlideToLoadPhoto(imageLink: String,view:SwipeRefreshLayout) {
    Glide.with(this)
        .load("https:$imageLink")
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                stopRefreshAnimationIfNeeded(view)
                this@useGlideToLoadPhoto.setImageResource(R.drawable.ic_load_error_vector)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                stopRefreshAnimationIfNeeded(view)
                return false
            }
        })
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_no_photo_vector)
                .centerCrop()
        )
        .into(this)
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