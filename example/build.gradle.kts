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
    type = "source_code"
}
