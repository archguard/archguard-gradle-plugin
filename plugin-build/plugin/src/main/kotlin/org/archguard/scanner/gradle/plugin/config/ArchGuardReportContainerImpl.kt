package org.archguard.scanner.gradle.plugin.config

import org.gradle.api.Task
import org.gradle.api.internal.CollectionCallbackActionDecorator
import org.gradle.api.reporting.ConfigurableReport
import org.gradle.api.reporting.DirectoryReport
import org.gradle.api.reporting.SingleFileReport
import org.gradle.api.reporting.internal.TaskGeneratedSingleDirectoryReport
import org.gradle.api.reporting.internal.TaskGeneratedSingleFileReport
import org.gradle.api.reporting.internal.TaskReportContainer

class ArchGuardReportContainerImpl : ArchGuardReportContainer, TaskReportContainer<ConfigurableReport> {
    constructor(task: Task, decorator: CollectionCallbackActionDecorator) : super(
        ConfigurableReport::class.java,
        task,
        decorator
    ) {
        add(TaskGeneratedSingleDirectoryReport::class.java, "html", task, "index.html")
        add(TaskGeneratedSingleFileReport::class.java, "xml", task)
        add(TaskGeneratedSingleFileReport::class.java, "csv", task)
    }

    override val html: DirectoryReport? get() = getByName("html") as DirectoryReport?
    override val xml: SingleFileReport? get() = getByName("xml") as SingleFileReport?
    override val csv: SingleFileReport? get() = getByName("csv") as SingleFileReport?
}
