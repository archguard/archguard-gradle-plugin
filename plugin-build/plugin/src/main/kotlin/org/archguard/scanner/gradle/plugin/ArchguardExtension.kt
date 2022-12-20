package org.archguard.scanner.gradle.plugin

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import javax.inject.Inject

@Suppress("UnnecessaryAbstractClass")
abstract class ArchguardExtension @Inject constructor(
    val project: Project,
    val slotContainer: NamedDomainObjectContainer<SlotConfiguration>
) {
    /**
     * The server url of Archguard backend, default to [http://localhost:8088]
     */
    var serverUrl: String = ""

    /**
     * The supported languages: [java, kotlin, javascript, typescript, python, go ...], default to [java]
     */
    var language: String = "java"

    /**
     * The supported features: ["apicalls", "datamap"]
     */
    var features: List<String> = listOf()

    /**
     * the source code path
     */
    var path: List<String> = listOf()

    /**
     * the output
     */
    var output: List<String> = listOf()

    /**
     * the systemId, can be ignored, if backend support update by repository
     */
    abstract var systemId: String

    /**
     * supported : source_code, git, diff_changes, sca, architecture, estimate
     */
    abstract var type: List<String>

    /**
     * The Archguard Slots configuration for the project, for examples:
     *
     * {
     *   "identifier": "rule",
     *   "host": "https://github.com/archguard/archguard/releases/download/v2.0.0-alpha.17",
     *   "version": "2.0.0-alpha.17",
     *   "jar": "rule-webapi-2.0.0-alpha.17-all.jar",
     *   "className": "org.archguard.linter.rule.webapi.WebApiRuleSlot",
     *   "slotType": "rule"
     * }
     */
    fun slots(action: Action<NamedDomainObjectContainer<SlotConfiguration>>) {
        action.execute(slotContainer)
    }
}
