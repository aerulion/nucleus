plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.3.6"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    id("maven-publish")
    id("org.shipkit.shipkit-auto-version") version "1.1.19"
    id("org.shipkit.shipkit-github-release") version "1.1.15"
    id("org.shipkit.shipkit-changelog") version "1.1.15"
}

group = "net.aerulion"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    paperDevBundle("1.18.2-R0.1-SNAPSHOT")
    compileOnly("com.zaxxer:HikariCP:5.0.1")
    compileOnly("org.jetbrains:annotations:22.0.0")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.aerulion"
            artifactId = "nucleus"
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/aerulion/nucleus")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

tasks.named<org.shipkit.changelog.GenerateChangelogTask>("generateChangelog") {
    previousRevision = project.ext.get("shipkit-auto-version.previous-tag") as String?
    githubToken = System.getenv("GITHUB_TOKEN")
    repository = "aerulion/nucleus"
}

tasks.named<org.shipkit.github.release.GithubReleaseTask>("githubRelease") {
    dependsOn(tasks.named("generateChangelog"))
    repository = "aerulion/nucleus"
    changelog = tasks.named<org.shipkit.changelog.GenerateChangelogTask>("generateChangelog")
        .get().outputFile
    githubToken = System.getenv("GITHUB_TOKEN")
    newTagRevision =
        System.getenv("GITHUB_SHA")
}

bukkit {
    name = "Nucleus"
    main = "net.aerulion.nucleus.Main"
    version = getVersion().toString()
    author = "aerulion"
    apiVersion = "1.18"
    libraries = listOf("com.zaxxer:HikariCP:5.0.1")
    commands {
        register("nucleus") {
            description = "The main nucleus command."
        }
    }
}
