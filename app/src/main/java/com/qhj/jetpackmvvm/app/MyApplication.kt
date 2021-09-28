package com.qhj.jetpackmvvm.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.qhj.base.data.DataStoreManager
import com.qhj.base.manager.TopActivityManager
import com.qhj.jetpackmvvm.R
import okhttp3.OkHttpClient

/**
 * Created by 7in on 2021-09-28 9:56
 */
class MyApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
        DataStoreManager.instance.init(this)

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                TopActivityManager.instance.setTopActivity(activity)
            }
            override fun onActivityResumed(activity: Activity) {
                TopActivityManager.instance.setTopActivity(activity)
            }
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityDestroyed(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityStopped(activity: Activity) {}
        })

        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .crossfade(500)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(this))
                    .build()
            }
            .build()
        Coil.setImageLoader(imageLoader)
    }
}