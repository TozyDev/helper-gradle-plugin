@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
    signing
    alias(libs.plugins.plugin.publish)
}

repositories {
    mavenCentral()
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(embeddedKotlinVersion)
        }

        val functionalTest by registering(JvmTestSuite::class) {
            testType = TestSuiteType.FUNCTIONAL_TEST
            useKotlinTest(embeddedKotlinVersion)

            dependencies {
                implementation(project())
            }
        }
    }
}

gradlePlugin {
    testSourceSets.add(sourceSets["functionalTest"])
    val helper by plugins.creating {
        val pluginId: String by project
        val pluginClass: String by project
        id = pluginId
        implementationClass = pluginClass
    }
}

signing {
    val signingKeyId: String? by project
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKeyId, signingKey?.replace("\\n", "\n"), signingPassword)
}

tasks {
    named<Task>("check") {
        dependsOn(testing.suites.named("functionalTest"))
    }

    validatePlugins {
        enableStricterValidation = true
    }
}
