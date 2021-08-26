package com.cocos.develop.dictionarykiss.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * homework com.cocos.develop.dictionarykiss.utils.rx
 *
 * @author Amina
 * 26.08.2021
 */
class SchedulerProvider : ISchedulerProvider {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()
}
