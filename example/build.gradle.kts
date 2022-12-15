plugins {
    java
    id("org.archguard.scanner")
}

archguard {
//    serverUrl = "http://localhost:8088"
    language = "kotlin"
    path += "."
    output += "json"
    systemId = "0"
}
