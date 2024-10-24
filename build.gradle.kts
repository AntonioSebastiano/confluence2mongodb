plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "atlassian-migrator"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.springframework.boot:spring-boot-starter-batch:3.3.4")
    implementation("org.springframework:spring-oxm:6.1.13")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.3.4")
    implementation("org.glassfish.jaxb:jaxb-runtime:4.0.5")
    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")

    implementation ("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")
    implementation("javax.xml.bind:jaxb-api:2.2.4")
    implementation ("org.glassfish.jaxb:jaxb-runtime:3.0.1")


    implementation(project(":wikimodel"))



    runtimeOnly("org.hsqldb:hsqldb:2.7.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.3")
}

tasks.test {
    useJUnitPlatform()
}