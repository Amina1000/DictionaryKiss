package com.cocos.develop.dictionarykiss.utils


import androidx.appcompat.widget.AppCompatImageView
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
            .into(this)
    }

}
