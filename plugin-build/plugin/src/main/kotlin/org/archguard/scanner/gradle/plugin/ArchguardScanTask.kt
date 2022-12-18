package org.archguard.scanner.gradle.plugin

import org.archguard.scanner.ctl.command.ScannerCommand
import org.archguard.scanner.ctl.loader.AnalyserLoader
import org.archguard.scanner.ctl.loader.AnalyserDispatcher
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class ArchguardScanTask : DefaultTask() {
    init {
        description = "The scanner for Archguard"
    }

    @get:Input
    abstract var command: ScannerCommand

    @TaskAction
    fun executeScan() {
        logger.lifecycle("Archguard scan task start")

        logger.lifecycle("Install Archguard path: ${AnalyserLoader.installPath}")
//
//        val context = CliScaContext(
//            client = command.buildClient(),
//            path = command.path,
//            language = command.language!!,
//        )
//
//        val spec = command.getAnalyserSpec(OfficialAnalyserSpecs.SCA.spec().identifier)
//
//        val analyser = AnalyserLoader.load(context, spec) as ScaAnalyser
//        logger.lifecycle("Analyser: ${analyser.context}")

        AnalyserDispatcher().dispatch(command)

        logger.lifecycle("Archguard scan task end")
    }
}
