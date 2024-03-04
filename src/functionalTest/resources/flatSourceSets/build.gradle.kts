import io.github.tozydev.helper.dsl.useFlatStructure

plugins {
    embeddedKotlin("jvm")
}

repositories {
    mavenCentral()
}

sourceSets {
    useFlatStructure()
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(embeddedKotlinVersion)
        }
    }
}
