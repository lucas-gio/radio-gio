import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.gioia"
//version = "v1.0.0"
var nitrite = "3.4.4"
var kodein = "7.14.0"
var gson = "2.9.0"
var log = "2.0.0-alpha7"
var vlc = "4.7.3"
var decompose = "1.0.0-alpha-02"
var coroutines = "1.6.4"
var navigation = "2.4.2"

plugins {
    kotlin("jvm") version "1.7.0"
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev753"
    id("org.sonarqube") version "3.4.0.2513"
}

sonarqube {
  properties {
      property("sonar.projectKey", "lucas-gio_radio-gio")
      property("sonar.organization", "lucas-gio")
      property("sonar.host.url", "https://sonarcloud.io")
  }
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://maven.google.com/")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(compose.desktop.currentOs)
    implementation(compose.materialIconsExtended)
    implementation(compose.foundation)
    implementation("org.dizitart:nitrite:$nitrite")
    implementation("org.kodein.di:kodein-di-framework-compose:$kodein")
    implementation("com.google.code.gson:gson:$gson")
    implementation("org.slf4j:slf4j-log4j12:$log")
    implementation("uk.co.caprica:vlcj:$vlc")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")
    implementation("com.arkivanov.decompose:decompose:$decompose")
    implementation("com.arkivanov.decompose:extensions-compose-jetbrains:$decompose")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>().all{
    kotlinOptions.jvmTarget = "16"
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

compose.desktop {
    application {
        //https://github.com/JetBrains/compose-jb/tree/master/tutorials/Native_distributions_and_local_execution
        mainClass = "com.gioia.radio.MainKt"
        // java home para el plugin de compilación nativa.
        javaHome = System.getenv("JDK_16")
        nativeDistributions {
            windows {
                //iconFile.set(project.file("icon.ico"))
            }
            linux {
                //iconFile.set(project.file("icon.png"))
            }
            targetFormats(
                TargetFormat.Deb,
                TargetFormat.Rpm,
                TargetFormat.Exe,
                TargetFormat.Msi)
            packageName = "Radio kotlin compose"
            //packageVersion = "v1.0.0"
            //version = "v1.0.0"
            description = "Radio kotlin compose, an online radio player for desktop and android."
            //appResourcesRootDir.set(project.layout.projectDirectory.file("file.db").asFile)
            appResourcesRootDir.set(project.layout.projectDirectory.dir("resources"))
        }
    }
}
