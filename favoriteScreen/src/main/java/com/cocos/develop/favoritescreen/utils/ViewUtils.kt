package com.cocos.develop.favoritescreen.utils

import androidx.appcompat.widget.AppCompatImageView
import com.cocos.develop.favoritescreen.R
import com.squareup.picasso.Picasso

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
            .placeholder(R.drawable.ic_no_photo_screen).fit().centerCrop()
            .into(this)
    }

}