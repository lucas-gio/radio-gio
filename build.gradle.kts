import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.compose") version "1.1.1"
}

group = "com.gioia"
version = "1.0"
var treeView = "1.2.0"
var nitrite = "3.4.4"
var kodein = "7.10.0"
var gson = "2.9.0"
var icons = "1.1.1"
var log = "1.7.36"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(compose.desktop.currentOs)
    implementation("cafe.adriel.bonsai:bonsai-core:$treeView")
    implementation("cafe.adriel.bonsai:bonsai-file-system:$treeView")
    implementation("cafe.adriel.bonsai:bonsai-json:$treeView")
    implementation("org.dizitart:nitrite:$nitrite")
    implementation("org.kodein.di:kodein-di-framework-compose:$kodein")
    implementation("com.google.code.gson:gson:$gson")
    implementation("org.jetbrains.compose.material:material-icons-extended-desktop:$icons")
    implementation("org.slf4j:slf4j-log4j12:$log")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>().all{
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "com.gioia.radio.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "untitled"
            packageVersion = "1.0.0"
        }
    }
}