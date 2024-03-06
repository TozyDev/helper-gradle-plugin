package io.github.tozydev.helper.dsl

import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer


private const val KOTLIN_SOURCE_SET_DIR = "kotlin"

fun SourceSet.useFlatStructure(srcDir: String, resourceDir: String) {
    val srcDirs = listOf(srcDir)
    java.setSrcDirs(srcDirs)
    resources.srcDirs(resourceDir)
    extensions.findByName(KOTLIN_SOURCE_SET_DIR)?.let {
        (it as SourceDirectorySet).setSrcDirs(srcDirs)
    }
}

fun SourceSetContainer.useFlatStructure() {
    named(SourceSet.MAIN_SOURCE_SET_NAME) {
        useFlatStructure("main", "resources")
    }
    named(SourceSet.TEST_SOURCE_SET_NAME) {
        useFlatStructure("test", "testResources")
    }
}
