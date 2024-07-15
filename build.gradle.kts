plugins {
    id("java")
}

val pluginName = "HunterPlugins"
val pluginVersion = "1.0"

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://repo.runelite.net/") }
    gradlePluginPortal()
}

val runeLiteVersion = "latest.release"

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
    testImplementation("junit:junit:4.12")

    compileOnly("net.runelite:client:$runeLiteVersion")
    testImplementation("net.runelite:client:$runeLiteVersion")
    testImplementation("net.runelite:jshell:$runeLiteVersion")

    compileOnly(files("libs/EthanVannPlugins-5.4.jar"))
    testImplementation(files("libs/EthanVannPlugins-5.4.jar"))

    compileOnly("org.pf4j:pf4j:3.6.0")
}

group = "com.hunter.plugins"
version = pluginVersion

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(11)
}

val pluginDir = file("src/main/java/com/hunter/plugins")
val plugins = pluginDir.listFiles()?.filter { it.isDirectory }?.map { it.name } ?: emptyList()

plugins.forEach { plugin ->
    tasks.register<Jar>("${plugin}Jar") {
        from(sourceSets.main.get().output)
        include("com/hunter/plugins/$plugin/**")
        archiveBaseName.set(plugin)
        archiveVersion.set(pluginVersion)
        destinationDirectory.set(file("$projectDir/release")) // Custom output directory
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}

tasks.register("buildAllPlugins") {
    dependsOn(plugins.map { tasks.named("${it}Jar") })
}

tasks.register<Copy>("copyJarToRelease") {
    from(layout.buildDirectory.file("libs/${pluginName}V2-${pluginVersion}.jar"))
    into(layout.projectDirectory.dir("release"))
}

tasks.named("build") {
    finalizedBy("copyJarToRelease")
}

tasks.test {
    useJUnitPlatform()
}
