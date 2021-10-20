package com.cocos.develop.dictionarykiss.domain

import io.reactivex.Observable

/**
 * homework com.cocos.develop.dictionarykiss.domain
 *
 * @author Amina
 * 26.08.2021
 */
interface DataSource<T> {
    fun getData(word: String): Observable<T>
}