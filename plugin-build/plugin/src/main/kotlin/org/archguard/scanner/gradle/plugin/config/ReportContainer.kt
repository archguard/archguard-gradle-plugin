package org.archguard.scanner.gradle.plugin.config

import org.gradle.api.reporting.ConfigurableReport
import org.gradle.api.reporting.DirectoryReport
import org.gradle.api.reporting.SingleFileReport
import org.gradle.api.reporting.internal.TaskReportContainer
import org.gradle.api.tasks.Internal

interface ArchGuardReportContainer: TaskReportContainer<ConfigurableReport> {
    @Internal
    fun getHtml(): DirectoryReport?

    @Internal
    fun getXml(): SingleFileReport?
}
