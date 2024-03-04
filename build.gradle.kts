plugins {
    `kotlin-dsl`
    signing
    alias(libs.plugins.plugin.publish)
}

repositories {
    mavenCentral()
}

gradlePlugin {
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
    validatePlugins {
        enableStricterValidation = true
    }
}
