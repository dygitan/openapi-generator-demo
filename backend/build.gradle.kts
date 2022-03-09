import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "github.dygitan"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

buildscript {
	repositories {
		gradlePluginPortal()
	}
	dependencies {
		classpath("org.openapitools:openapi-generator-gradle-plugin:5.4.0")
	}
}

configure<SourceSetContainer> {
	kotlin.sourceSets {
		named("main") {
			kotlin.srcDirs("src/main/kotlin", "$buildDir/generated/src/main/kotlin")
		}
	}
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

plugins {
	val kotlinVersion = "1.6.10"

	kotlin("jvm") version kotlinVersion

	kotlin("plugin.jpa") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion

	id("io.spring.dependency-management") version "1.0.11.RELEASE"

	id("org.openapi.generator") version "5.4.0"
	id("org.springframework.boot") version "2.6.3"
}

repositories {
	mavenCentral()
}

tasks.create("openApiGenerate_Backend", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
	generatorName.set("kotlin-spring")
	inputSpec.set("$rootDir/src/main/resources/openapi/specs.yaml")
	outputDir.set("$buildDir/generated")

	apiPackage.set("github.dygitan.openapi.demo.generated.api")
	modelPackage.set("github.dygitan.openapi.demo.generated.model")
	packageName.set("github.dygitan.openapi.demo.generated")

	configOptions.set(mapOf(
		"interfaceOnly" to "true",
		"useTags" to "true"
	))
}

tasks.withType<KotlinCompile> {
	dependsOn("openApiGenerate_Backend")

	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
//	useJUnitPlatform()
}
