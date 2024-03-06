package io.github.tozydev.helper.plugins

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply
import org.gradle.toolchains.foojay.FoojayToolchainsConventionPlugin

abstract class HelperSettingsPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {
        settings.apply<FoojayToolchainsConventionPlugin>()
    }
}
