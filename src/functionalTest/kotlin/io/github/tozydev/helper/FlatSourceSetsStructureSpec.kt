package io.github.tozydev.helper

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.copyToRecursively
import kotlin.io.path.toPath
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class FlatSourceSetsStructureSpec {
    @TempDir
    lateinit var projectDir: Path

    @OptIn(ExperimentalPathApi::class)
    @BeforeTest
    fun setup() {
        FlatSourceSetsStructureSpec::class.java.getResource("/flatSourceSets")?.toURI()?.toPath()
            ?.copyToRecursively(projectDir, followLinks = false)
    }

    @Test
    fun `should build a project with flat source sets structure`() {
        val result =
            GradleRunner.create()
                .withProjectDir(projectDir.toFile())
                .withArguments("build")
                .withPluginClasspath()
                .build()

        assertNotEquals(TaskOutcome.NO_SOURCE, result.task(":processResources")?.outcome)
        assertNotEquals(TaskOutcome.NO_SOURCE, result.task(":compileKotlin")?.outcome)
        assertNotEquals(TaskOutcome.NO_SOURCE, result.task(":compileJava")?.outcome)
        assertNotEquals(TaskOutcome.NO_SOURCE, result.task(":compileKotlinTest")?.outcome)
        assertEquals(TaskOutcome.SUCCESS, result.task(":build")?.outcome)
    }
}
