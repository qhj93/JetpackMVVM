package com.qhj.gradle.dependencies

/**
 * Created by 7in on 2021-09-27 17:26
 */
object AndroidX {
    /**
     * appcompat中默认引入了很多库(比如activity库、fragment库、core库、annotation库、drawerLayout库、appcompat-resources)
     * 如果想使用其中某个库的更新版本，可以单独引用
     * 提示：对于声明式依赖，同一个库的不同版本，gradle会自动使用最新版本来进行依赖替换、编译
     */
    const val appcompat = "androidx.appcompat:appcompat:1.3.1"

    //core包+ktx扩展函数
    const val coreKtx = "androidx.core:core-ktx:1.6.0"

    //activity+ktx扩展函数
    const val activityKtx = "androidx.activity:activity-ktx:1.3.1"

    //fragment+ktx扩展函数
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.5"

    //约束布局
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0"

    //本地数据存储，替代SP
    const val Datastore = "androidx.datastore:datastore-preferences:1.0.0"

    //dex分包
    const val multidex = "androidx.multidex:multidex:2.0.1"

    /**
     * 生命周期感知型组件，可执行操作来响应另一个组件（如 Activity 和 Fragment）的生命周期状态的变化。
     */
    object Lifecycle {
        private const val lifecycle_version = "2.3.1"
        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    }
}