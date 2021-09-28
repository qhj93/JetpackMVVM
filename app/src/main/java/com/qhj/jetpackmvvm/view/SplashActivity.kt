package com.qhj.jetpackmvvm.view

import androidx.lifecycle.viewModelScope
import coil.load
import com.alibaba.android.arouter.launcher.ARouter
import com.qhj.base.constant.RouterPath
import com.qhj.base.data.DataStoreKey
import com.qhj.base.data.DataStoreManager
import com.qhj.base.mvvm.BaseActivity
import com.qhj.base.mvvm.BaseViewModel
import com.qhj.jetpackmvvm.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by 7in on 2021-09-28 14:31
 */
class SplashActivity : BaseActivity<ActivitySplashBinding,BaseViewModel>(ActivitySplashBinding::inflate,BaseViewModel::class.java){

    override fun init() {
        viewModel.viewModelScope.launch {
            DataStoreManager.instance.getStringData(DataStoreKey.IMG_URL).collect {
                binding.iv.load(it)
            }
        }
        viewModel.viewModelScope.launch {
            delay(3000)
            ARouter.getInstance().build(RouterPath.MAIN_ACTIVITY).navigation()
            finish()
        }
    }

}