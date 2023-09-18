plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotestVersion = "5.6.2" // works
// val kotestVersion = "5.7.2 // doesn't work

dependencies {
    testImplementation(group = "io.projectreactor.tools", name = "blockhound", version = "1.0.8.RELEASE")
    testImplementation(group = "io.kotest", name = "kotest-assertions-core", version = kotestVersion)
    testImplementation(group = "io.kotest", name = "kotest-extensions-blockhound", version = kotestVersion)
    testImplementation(group = "io.kotest", name = "kotest-runner-junit5", version = kotestVersion)
}

tasks.test {
    useJUnitPlatform()
    jvmArgs = listOf(
        "-XX:+AllowRedefinitionToAddDeleteMethods",
    )
}

kotlin {
    jvmToolchain(19)
}

application {
    mainClass.set("MainKt")
}