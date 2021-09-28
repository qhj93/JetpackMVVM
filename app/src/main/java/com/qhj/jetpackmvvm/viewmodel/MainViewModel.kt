package com.qhj.jetpackmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.qhj.base.data.DataStoreKey
import com.qhj.base.data.DataStoreManager
import com.qhj.base.mvvm.BaseViewModel
import com.qhj.jetpackmvvm.bean.GankBean
import com.qhj.jetpackmvvm.model.MainRepository
import kotlinx.coroutines.launch

/**
 * Created by 7in on 2021-09-28 9:30
 */
class MainViewModel : BaseViewModel(){
    private val repository: MainRepository by lazy { MainRepository() }

    fun getImgList(): LiveData<GankBean> {
        return request(true) { repository.getRepositoryData() }
    }

    fun saveData(imgUrl: String){
        viewModelScope.launch {
            DataStoreManager.instance.putStringData(DataStoreKey.IMG_URL,imgUrl)
        }
    }
}