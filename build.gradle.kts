plugins {
    kotlin("jvm") version "2.0.20"
    id("io.gitlab.arturbosch.detekt") version "1.23.8" // Замените на актуальную версию Detekt
}

group = "ru.job4j"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    //testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    testImplementation("org.assertj:assertj-core:3.24.2")
}

detekt {
    toolVersion = "1.23.8" // Версия Detekt
    config = files("$projectDir/config/detekt/detekt.yml") // Путь к конфигурационному файлу
    buildUponDefaultConfig = true // Настройки будут расширять дефолтный конфиг Detekt
    allRules = false // Использовать только правила, указанные в конфиге
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    reports {
        html.required.set(true) // Отчет в формате HTML
        xml.required.set(true)  // Отчет в формате XML
        txt.required.set(false) // Текстовый отчет
    }
}
