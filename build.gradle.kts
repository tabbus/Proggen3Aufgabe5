plugins {
    id("java")
    application
}

group = "de.medieninformatik"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainModule.set("${group}.${rootProject.name}")  // nur für modulare Anwendungen
    mainClass.set("${mainModule.get().lowercase()}.Main")
}

tasks.test {
    useJUnitPlatform()
}