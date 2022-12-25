package org.archguard.scanner.gradle.plugin.task

import org.archguard.scanner.ctl.command.ScannerCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class LocalCheckTask : DefaultTask() {
    init {
        description = "Archguard check in local"
    }

    @get:Input
    abstract var commands: List<ScannerCommand>

    @TaskAction
    fun executeScan() {
        logger.lifecycle("ArchGuardLocalCheckTask")

        val outputDir = project.buildDir.resolve("archguard")
        outputDir.mkdirs()

        logger.lifecycle("OutputDir: ${outputDir.absolutePath}")
    }
}
