package com.qhj.jetpackmvvm.bean

/**
 * Created by 7in on 2021-09-28 16:11
 */
data class GankBean(
    var error: Boolean,
    var results: List<GankResultBean>?
)

data class GankResultBean(var url: String)