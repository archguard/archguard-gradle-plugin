package org.archguard.scanner.gradle.plugin.task

import org.archguard.scanner.ctl.command.ScannerCommand
import org.archguard.scanner.ctl.loader.AnalyserDispatcher
import org.archguard.scanner.ctl.loader.AnalyserLoader
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class ScanTask : DefaultTask() {
    init {
        description = "The scanner for Archguard"
    }

    @get:Input
    abstract var commands: List<ScannerCommand>

    @TaskAction
    fun executeScan() {
        logger.lifecycle("Archguard scan task start")

        logger.lifecycle("Archguard Installed path: ${AnalyserLoader.installPath}")

        commands.forEach { command ->
            logger.lifecycle("exec command type: ${command.type}, path: ${command.path}")

            AnalyserDispatcher().dispatch(command)
        }

        logger.lifecycle("Archguard scan task end")
    }
}
