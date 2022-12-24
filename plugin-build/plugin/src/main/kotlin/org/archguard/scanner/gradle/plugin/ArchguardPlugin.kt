package org.archguard.scanner.gradle.plugin

import org.archguard.scanner.core.AnalyserSpec
import org.archguard.scanner.ctl.command.ScannerCommand
import org.archguard.scanner.gradle.plugin.config.ArchGuardReportContainerImpl
import org.archguard.scanner.gradle.plugin.config.ArchguardConfig
import org.archguard.scanner.gradle.plugin.config.SlotConfiguration
import org.archguard.scanner.gradle.plugin.config.SlotConfigurationFactory
import org.archguard.scanner.gradle.plugin.config.SpecConfiguration
import org.archguard.scanner.gradle.plugin.config.SpecConfigurationFactory
import org.archguard.scanner.gradle.plugin.task.LocalCheckTask
import org.archguard.scanner.gradle.plugin.task.ScanTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.CollectionCallbackActionDecorator
import javax.inject.Inject

const val EXTENSION_NAME = "archguard"

abstract class ArchguardPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val slotContainer = project.container(SlotConfiguration::class.java, SlotConfigurationFactory(project))
        val specContainer = project.container(SpecConfiguration::class.java, SpecConfigurationFactory(project))

        val extension = project.extensions.create(
            EXTENSION_NAME,
            ArchguardConfig::class.java,
            project,
            slotContainer,
            specContainer
        )

        // normal archguard scanning task
        project.tasks.register("archguardScan", ScanTask::class.java) {
            it.commands = toCommands(extension, project)
            it.group = "verification"
            it.description = "Scan the project with Archguard"
        }

        // local archguard scanning task
        project.tasks.register("archguardLocalCheck", LocalCheckTask::class.java) {
            extension.report = ArchGuardReportContainerImpl(it, getCallbackActionDecorator())
            it.commands = toCommands(extension, project)
            it.group = "verification"
            it.description = "Run ArchGuard in Local"
        }
    }
}

@Inject
fun getCallbackActionDecorator(): CollectionCallbackActionDecorator {
    return CollectionCallbackActionDecorator.NOOP
}

private fun toCommands(extension: ArchguardConfig, project: Project): List<ScannerCommand> {
    return extension.type.map { type ->
        val path = project.projectDir.resolve(extension.path).absolutePath

        ScannerCommand(
            serverUrl = extension.serverUrl,
            language = extension.language,
            features = extension.features,
            path = path,
            output = extension.output,
            type = org.archguard.scanner.core.context.AnalyserType.fromString(type),
            systemId = extension.systemId,
            slots = extension.slotContainer.map { slot ->
                AnalyserSpec(
                    identifier = slot.identifier,
                    host = slot.host,
                    version = slot.version,
                    jar = slot.jar,
                    className = slot.className
                )
            }.toList()
        )
    }
}
