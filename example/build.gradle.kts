buildscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

plugins {
    java
    id("org.archguard.scanner")
    id("org.springframework.boot") version "2.7.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc:2.7.0")
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.7.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.7.0")
    implementation("org.springframework.boot:spring-boot-starter-websocket:2.7.0")
}

archguard {
    serverUrl = "http://localhost:8088"
    language = "java"
    output = listOf("json")
    systemId = "0"
    type = listOf("source_code", "sca", "estimate")
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
