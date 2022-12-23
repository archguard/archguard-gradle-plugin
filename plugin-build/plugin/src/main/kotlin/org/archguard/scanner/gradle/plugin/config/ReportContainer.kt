package org.archguard.scanner.gradle.plugin.config

import org.gradle.api.reporting.ConfigurableReport
import org.gradle.api.reporting.DirectoryReport
import org.gradle.api.reporting.ReportContainer
import org.gradle.api.reporting.SingleFileReport
import org.gradle.api.tasks.Internal

interface ArchGuardReportContainer: ReportContainer<ConfigurableReport?> {
    @get:Internal
    val html: DirectoryReport?

    @get:Internal
    val xml: SingleFileReport?

    @get:Internal
    val csv: SingleFileReport?
}
