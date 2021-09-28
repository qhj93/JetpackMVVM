package com.qhj.gradle.plugins

import org.gradle.api.Project

/**
 * Created by 7in on 2021-09-27 18:18
 */
internal fun Project.configurePlugins(){
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")
}