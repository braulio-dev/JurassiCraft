import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
}

repositories {
    mavenCentral()
}

dependencies {
    runtimeOnly("org.lwjgl:lwjgl:3.3.3:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-glfw:3.3.3:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-opengl:3.3.3:natives-windows")
    implementation("org.lwjgl:lwjgl:3.3.3")
    implementation("org.lwjgl:lwjgl-glfw:3.3.3")
    implementation("org.lwjgl:lwjgl-shaderc:3.3.3")
    implementation("org.lwjgl:lwjgl-opengl:3.3.3")
    implementation("org.lwjgl:lwjgl-stb:3.3.3")
    implementation("org.joml:joml:1.9.25")
    implementation("io.github.microutils:kotlin-logging:2.0.11")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.google.guava:guava:33.0.0-jre")
    implementation("org.l33tlabs.twl:pngdecoder:1.0")
    implementation("com.github.ben-manes.caffeine:caffeine:3.0.0")
    implementation("org.apache.commons:commons-io:1.3.2")
    testImplementation(kotlin("test-junit"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

val lwjglNatives by tasks.registering(Copy::class) {
    configurations.compileOnly.configure {
        isCanBeResolved = true
    }
    from(configurations.compileOnly.get().files)
    into("$buildDir/libs")
}

tasks.named("build") {
    dependsOn(lwjglNatives)
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin")
        resources.srcDirs("src/main/resources")
    }
    test {
        java.srcDirs("src/test/kotlin")
        resources.srcDirs("src/test/resources")
    }
}

tasks.withType<Copy> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}