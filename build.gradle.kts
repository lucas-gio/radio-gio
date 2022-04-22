import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0"
}

group = "com.gioia"
version = "1.0"
var treeViewVersion = "1.1.0"
var materialIconsVersion = "1.1.1"
var nitriteVersion = "3.4.4"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(compose.desktop.currentOs)
    implementation("cafe.adriel.bonsai:bonsai-core:${treeViewVersion}")
    implementation("cafe.adriel.bonsai:bonsai-file-system:${treeViewVersion}")
    implementation("cafe.adriel.bonsai:bonsai-json:${treeViewVersion}")
    implementation("org.dizitart:nitrite:${nitriteVersion}")
    //implementation("org.jetbrains.compose.material:material-icons-extended-desktop:${materialIconsVersion}")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "untitled"
            packageVersion = "1.0.0"
        }
    }
}