package io.github.tozydev.helper.plugins

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

abstract class HelperSettingsPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {
    }
}
