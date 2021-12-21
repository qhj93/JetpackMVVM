package com.qhj.jetpackmvvm.view

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qhj.base.constant.EventKey
import com.qhj.base.constant.RouterPath
import com.qhj.base.eventbus.EventBus
import com.qhj.base.mvvm.BaseActivity
import com.qhj.jetpackmvvm.adapter.MyRecyclerViewAdapter
import com.qhj.jetpackmvvm.bean.GankResultBean
import com.qhj.jetpackmvvm.databinding.ActivityMainBinding
import com.qhj.jetpackmvvm.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@Route(path = RouterPath.MAIN_ACTIVITY)
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate, MainViewModel::class.java) {

    override fun init() {
        binding.rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val data: MutableList<GankResultBean> = ArrayList()
        val adapter = MyRecyclerViewAdapter(data)
        binding.rv.adapter = adapter

        viewModel.getImgList().observe(this, {
                data.addAll(it.results!!)
                adapter.notifyDataSetChanged()
                viewModel.saveData(data[0].url)
            })

        binding.floatBT.setOnClickListener {
            ARouter.getInstance().build(RouterPath.IMAGE_ACTIVITY).navigation()
        }

        EventBus.instance.with<String>(EventKey.START_SHOW_IMG).register(this){
            viewModel.viewModelScope.launch {
                for (i in 3 downTo 1){
                    EventBus.instance.with<String>(EventKey.SHOW_IMG).post(i.toString())
                    delay(1000)
                }
                for (bean in data) {
                    EventBus.instance.with<String>(EventKey.SHOW_IMG).post(bean.url)
                    delay(1000)
                }
            }
        }

        //第二种网络请求封装方式
        viewModel.getData2()
        viewModel.liveData.observer(this){
            onSuccess {
                Log.d("xxx", "onSuccess: ")
            }
            onFailure { code, msg ->
                Log.d("xxx", "onFailure: $code, $msg")
            }
            onError {
                Log.d("xxx", "onError: ${it.message}")
            }
        }
    }

}