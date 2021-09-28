package com.qhj.librarya

import android.view.View
import coil.load
import com.alibaba.android.arouter.facade.annotation.Route
import com.qhj.base.constant.EventKey
import com.qhj.base.constant.RouterPath
import com.qhj.base.eventbus.EventBus
import com.qhj.base.mvvm.BaseActivity
import com.qhj.base.mvvm.BaseViewModel
import com.qhj.librarya.databinding.ActivityImgBinding

/**
 * Created by 7in on 2021-09-28 11:37
 */
@Route(path = RouterPath.IMAGE_ACTIVITY)
class ImageActivity : BaseActivity<ActivityImgBinding,BaseViewModel>(ActivityImgBinding::inflate,BaseViewModel::class.java){

    override fun init() {
        binding.tv.setOnClickListener {
            EventBus.instance.with<String>(EventKey.START_SHOW_IMG).post("start")
            binding.tv.isClickable =false
        }

        EventBus.instance.with<String>(EventKey.SHOW_IMG).register(this){
            if (it.startsWith("http")){
                binding.tv.visibility = View.GONE
                binding.iv.load(it)
            } else{
                binding.tv.text = it
            }
        }
    }

}