package com.cocos.develop.dictionarykiss.utils.rx

import io.reactivex.Scheduler

/**
 * homework com.cocos.develop.dictionarykiss.utils.rx
 *
 * @author Amina
 * 26.08.2021
 */
//In the sake of testing
interface ISchedulerProvider {

    fun ui(): Scheduler

    fun io(): Scheduler
}
