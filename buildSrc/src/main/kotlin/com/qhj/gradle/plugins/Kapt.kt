package com.qhj.gradle.plugins

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

/**
 * Created by 7in on 2021-09-27 18:18
 * Arouter统一配置
 */
internal fun Project.configureKapt() = this.extensions.getByType<KaptExtension>().run {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}