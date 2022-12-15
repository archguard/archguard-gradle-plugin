plugins {
    java
    id("org.archguard.scanner")
}

archguard {
    serverUrl = "http://localhost:8088"
    language = "kotlin"
    path += "src/main"
    output += "http"
    systemId = "0"
}
