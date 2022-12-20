plugins {
    java
    id("org.archguard.scanner")
}

archguard {
    serverUrl = "http://localhost:8088"
    language = "java"
    path += "../"
    output = listOf("json")
    systemId = "0"
    type = listOf("source_code", "sca")
    features = listOf("apicalls", "datamap")

    slots {
        create("slot") {
            identifier = "rule"
            host = "https://github.com/archguard/archguard/releases/download/v2.0.0-beta.5"
            version = "2.0.0-beta.5"
            jar = "rule-webapi-2.0.0-beta.5-all.jar"
            className = "org.archguard.linter.rule.webapi.WebApiRuleSlot"
        }
    }
}
