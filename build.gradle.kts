@file:Suppress("UnstableApiUsage")

fun Project.envOrProp(env: String, prop: String): Provider<String> =
    providers.environmentVariable(env).orElse(providers.gradleProperty(prop))

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
    useInMemoryPgpKeys(
        envOrProp("GPG_KEY_ID", "gpg.keyId").orNull,
        envOrProp("GPG_KEY", "gpg.key").map { it.replace("\\n", "\n") }.orNull,
        envOrProp("GPG_PASSWORD", "gpg.password").orNull
    )
}

tasks {
    named<Task>("check") {
        dependsOn(testing.suites.named("functionalTest"))
    }

    validatePlugins {
        enableStricterValidation = true
    }
}

publishing {
    repositories {
        maven("https://maven.fury.io/grassmc/") {
            name = "fury"
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                username = envOrProp("FURY_TOKEN", "fury.token").orNull
                password = envOrProp("FURY_PASSWORD", "fury.password").orNull
            }
        }
    }
}
