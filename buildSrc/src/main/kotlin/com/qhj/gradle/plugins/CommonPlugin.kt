package com.qhj.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by 7in on 2021-09-27 18:18
 */
class CommonPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.configurePlugins()
        project.configureAndroid()
        project.configureKapt()
    }
}