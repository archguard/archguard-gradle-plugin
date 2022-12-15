plugins {
    java
    id("org.archguard.scanner")
}

archguard {
    serverUrl = "http://localhost:8088"
    language = "kotlin"
    features += "apicalls"
    path += "src/main"
    output += "http"

    slots {
        create("slot") {
            identifier = "rule"
            host = "https://github.com/archguard/archguard/releases/download/v2.0.0-alpha.17"
            version = "2.0.0-alpha.17"
            jar = "rule-webapi-2.0.0-alpha.17-all.jar"
            className = "org.archguard.linter.rule.webapi.WebApiRuleSlot"
            slotType = "rule"
        }
    }
}
