plugins {
    java
    id("org.springframework.boot") version "3.5.7"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm")
    id("com.google.cloud.tools.jib").version("3.4.3")
}

group = "io.copebble.prayerhouse"
version = "0.0.1-SNAPSHOT"
description = "prayerhouse-cicd-demo"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

val mockitoAgent = configurations.create("mockitoAgent")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-web")

    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")
    testCompileOnly("org.projectlombok:lombok:1.18.42")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.42")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(kotlin("stdlib-jdk8"))

    // Mockito dependencies
    testImplementation("org.mockito:mockito-core:5.18.0")
    mockitoAgent("org.mockito:mockito-core") { isTransitive = false }
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
}

jib {
    from {
        // docker image 확인할 때 support 가능 platform 확인해야 함
        image = "eclipse-temurin:21-jre-ubi9-minimal"
        platforms {
            platform {
                // raspberry pi debian os(arm64) 대응
                architecture = "arm64"
                os = "linux"
            }
        }
    }

    to {
        tags = setOf(System.getProperty("deployTag"))
    }

    container {
        // jvmFlags (jvmOptions)
        creationTime = "USE_CURRENT_TIMESTAMP"
        user = "nobody"
    }
}