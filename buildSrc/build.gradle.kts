import org.gradle.kotlin.dsl.`kotlin-dsl`

/**
 * 当运行 Gradle 时会检查项目中是否存在一个名为 buildSrc 的目录。
 * 然后 Gradle 会自动编译并测试这段代码，并将其放入构建脚本的类路径中,
 * 对于多项目构建，只能有一个 buildSrc 目录，
 * 该目录必须位于根项目目录中, buildSrc 是 Gradle 项目根目录下的一个目录，
 * 它可以包含我们的构建逻辑，与脚本插件相比，buildSrc 应该是首选，因为它更易于维护、重构和测试代码。
 * 如果追求编译速度可使用ComposingBuild(复合构建)方案
 */

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    /* Example Dependency */
    /* Depend on the android gradle plugin, since we want to access it in our plugin */
    implementation("com.android.tools.build:gradle:4.1.1")

    /* Example Dependency */
    /* Depend on the kotlin plugin, since we want to access it in our plugin */
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")

    /* Depend on the default Gradle API's since we want to build a custom plugin */
    implementation(gradleApi())
    implementation(localGroovy())
}

/**
 * 禁用测试报告（Gradle 默认会自动创建测试报告）
 */
tasks.withType<Test> {
    reports.html.isEnabled = false
    reports.junitXml.isEnabled = false
}

/**
 * isFork：将编译器作为单独的进程运行。
 * 该过程在构建期间将被重用，因此分叉开销很小。分叉的好处是，内存密集型编译是在不同的过程中进行的，从而导致主 Gradle 守护程序中的垃圾回收量大大减少。
 * 守护程序中较少的垃圾收集意味着 Gradle 的基础架构可以运行得更快，尤其是在您还使用的情况下 --parallel。
 *
 * isIncremental：增量编译。Gradle 可以分析直至单个类级别的依赖关系，以便仅重新编译受更改影响的类。自 Gradle 4.10 起，增量编译是默认设置。
 */
tasks.withType<JavaCompile> {
    options.isFork = true
    options.isIncremental = true
}

/**
 * 禁用关于使用实验性 Kotlin 编译器功能的警告
 */
kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
