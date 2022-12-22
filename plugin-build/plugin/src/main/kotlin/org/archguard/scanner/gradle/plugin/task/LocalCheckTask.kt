package org.archguard.scanner.gradle.plugin.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class LocalCheckTask : DefaultTask() {
    init {
        description = "Archguard check in local"
    }

    @TaskAction
    fun executeScan() {
        println("ArchGuardCheckTask")
    }
}
