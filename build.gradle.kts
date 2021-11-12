import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.panteleyev.jpackageplugin") version "1.3.1"
    kotlin("jvm") version "1.4.32"
}

group = "org.dt.bexon."
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.5.31")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "14"
}

task("copyDependencies", Copy::class) {
    from(configurations.runtimeClasspath).into("$buildDir/jmods")
}

task("copyJar", Copy::class) {
    from(tasks.jar).into("$buildDir/jmods")
}

tasks.jpackage {
    dependsOn("build", "copyDependencies", "copyJar")

    appName = "Test App"
    appVersion = project.version.toString()
    vendor = "dt.bexon"
    copyright = "Copyright (c) 2020 Bexon Pak"
    runtimeImage = System.getProperty("java.home")
    module = "org.dt.bexon.blg/org.dt.bexon.blg.MainKt"
    modulePaths = listOf("$buildDir/jmods")
    destination = "$buildDir/dist"
    javaOptions = listOf("-Dfile.encoding=UTF-8")

    mac {
    }

    windows {
        winMenu = true
        winDirChooser = true
    }
}