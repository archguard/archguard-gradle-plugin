package org.archguard.scanner.gradle.plugin.task

import org.archguard.scanner.ctl.command.ScannerCommand
import org.archguard.scanner.ctl.loader.AnalyserDispatcher
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class LocalCheckTask : DefaultTask() {
    init {
        description = "Archguard check in local"
    }

    @get:Input
    abstract var commands: List<ScannerCommand>
//
//    @get:OutputDirectory
//    abstract val outputDirectory: DirectoryProperty

    @TaskAction
    fun executeScan() {
        logger.lifecycle("ArchGuardLocalCheckTask")

        val outputDir = project.buildDir.resolve("archguard")
        outputDir.mkdirs()

        logger.lifecycle("OutputDir: ${outputDir.absolutePath}")

//        File(outputDirectory.asFile.get(), "lint.json")
    }
}
