package org.archguard.scanner.gradle.plugin.config

import org.gradle.api.Named
import org.gradle.api.NamedDomainObjectFactory
import org.gradle.api.Project
import javax.inject.Inject

// refactor -> merge SlotConfiguration and SpecConfiguration
abstract class SpecConfiguration @Inject constructor(
    name: String,
    private var project: Project
) : Named {
    private val mName: String = name
    override fun getName(): String {
        return this.mName
    }

    abstract var identifier: String
    abstract var host: String
    abstract var version: String
    abstract var jar: String
    abstract var className: String
    abstract var slotType: String
}

internal class SpecConfigurationFactory(private val project: Project) : NamedDomainObjectFactory<SpecConfiguration> {
    override fun create(name: String): SpecConfiguration {
        return project.objects.newInstance(SpecConfiguration::class.java, name, project)
    }
}
