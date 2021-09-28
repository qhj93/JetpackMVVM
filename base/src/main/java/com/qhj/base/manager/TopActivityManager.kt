package com.qhj.base.manager

import android.app.Activity
import java.lang.ref.WeakReference

/**
 * Created by 7in on 2021-09-28 9:03
 * DCL单例模式
 * 构造函数私有化
 */
class TopActivityManager {
    private lateinit var topActivityWeakRef: WeakReference<Activity>

    companion object{
        val instance: TopActivityManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED){ TopActivityManager() }
    }

    fun setTopActivity(activity: Activity){
        topActivityWeakRef = WeakReference(activity)
    }

    fun getTopActivity() : Activity?{
        return topActivityWeakRef.get()
    }
}