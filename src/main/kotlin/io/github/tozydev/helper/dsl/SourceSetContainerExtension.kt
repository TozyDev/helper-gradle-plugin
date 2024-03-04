package io.github.tozydev.helper.dsl

import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer

fun SourceSetContainer.useFlatStructure() {
    named(SourceSet.MAIN_SOURCE_SET_NAME) {
        val srcDirs = listOf("src")
        java.setSrcDirs(srcDirs)
        resources.srcDirs("resources")
        extensions.findByName("kotlin")?.let {
            (it as SourceDirectorySet).setSrcDirs(srcDirs)
        }
    }
    named(SourceSet.TEST_SOURCE_SET_NAME) {
        val srcDirs = listOf("test")
        java.setSrcDirs(srcDirs)
        resources.srcDirs("testResources")
        extensions.findByName("kotlin")?.let {
            (it as SourceDirectorySet).setSrcDirs(srcDirs)
        }
    }
}
