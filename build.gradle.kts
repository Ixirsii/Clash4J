plugins {
    id("java-library")
    id("checkstyle")
    id("jacoco")
    id("maven-publish")
    id("signing")

    alias(libs.plugins.axion)
    alias(libs.plugins.lombok)
    alias(libs.plugins.nexus.publish)
}

group = "tech.ixirsii"
version = scmVersion.version

repositories {
    mavenCentral()
}

val mockitoAgent = configurations.create("mockitoAgent")

dependencies {
    api(platform(libs.reactor.bom))
    api(libs.reactor.core)

    implementation(libs.bundles.jackson)
    implementation(libs.okhttp)
    implementation(libs.slf4j.api)

    mockitoAgent(libs.mockito.core) { isTransitive = false }

    testImplementation(libs.bundles.mockito)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.bundles.junit)

    testRuntimeOnly(libs.junit.platform.launcher)
    testRuntimeOnly(libs.logback.classic)
}

checkstyle {
    toolVersion = "10.23.0"
}

jacoco {
    toolVersion = "0.8.13"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

nexusPublishing {
    val sonatypeUsername: String? by project
    val sonatypePassword: String? by project

    repositories {
        sonatype {
            nexusUrl = uri("https://s01.oss.sonatype.org/service/local/")
            snapshotRepositoryUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            username = sonatypeUsername
            password = sonatypePassword
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                groupId = "tech.ixirsii"
                name = "clash4j"
                description = "Clash4J is a Java library for the Clash of Clans API."
                url = "https://github.com/Ixirsii/Clash4J"
                developers {
                    developer {
                        id = "Ixirsii"
                        name = "Ryan Porterfield"
                        email = "ixirsii@ixirsii.tech"
                    }
                }
                licenses {
                    license {
                        name = "BSD 3-Clause"
                        url = "https://opensource.org/license/bsd-3-clause/"
                    }
                }
                scm {
                    connection = "scm:git:git@github.com:Ixirsii/Clash4J.git"
                    developerConnection = "scm:git:git@github.com:Ixirsii/Clash4J.git"
                    url = "https://github.com/Ixirsii/Clash4J.git"
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ixirsii/Clash4J")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)

    sign(publishing.publications)
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}

tasks.checkstyleTest {
    configFile = file("${rootDir}/config/checkstyle/checkstyle-test.xml")
}

tasks.withType<Checkstyle>().configureEach {
    reports {
        html.required = true
        xml.required = false
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        val coverageExclusions = listOf(
            "tech.ixirsii.clash.internal"
        )

        rule {
            excludes = coverageExclusions
            limit {
                counter = "CLASS"
                element = "CLASS"
                minimum = 1.0.toBigDecimal()
            }
        }
        rule {
            excludes = coverageExclusions
            limit {
                counter = "METHOD"
                element = "CLASS"
                minimum = 1.0.toBigDecimal()
            }
        }
        rule {
            excludes = coverageExclusions
            limit {
                counter = "LINE"
                element = "CLASS"
                minimum = 0.70.toBigDecimal()
            }
        }
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        csv.required = false
        html.required = true
        xml.required = true
    }
}

tasks.register<Test>("integrationTest") {
    description = "Run integration tests."
    group = "verification"

    jvmArgs("-javaagent:${mockitoAgent.asPath}")
    useJUnitPlatform() {
        includeTags("integration")
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
    useJUnitPlatform() {
        excludeTags("integration")
    }
}
