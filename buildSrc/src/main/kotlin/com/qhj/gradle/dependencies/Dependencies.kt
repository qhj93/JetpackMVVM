@file:Suppress("SpellCheckingInspection")

package com.qhj.gradle.dependencies

/**
 * Created by 7in on 2021-09-27 18:18
 */
object Dependencies {
    const val material = "com.google.android.material:material:1.2.1"

    const val gson = "com.google.code.gson:gson:2.8.6"

    const val coil = "io.coil-kt:coil:1.3.2"

    object Arouter {
        private const val arouter_version = "1.5.2"
        const val arouter = "com.alibaba:arouter-api:${arouter_version}"
        const val arouterCompiler = "com.alibaba:arouter-compiler:${arouter_version}"
    }

    object Retrofit {
        private const val retrofit_version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:${retrofit_version}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${retrofit_version}"
    }

}