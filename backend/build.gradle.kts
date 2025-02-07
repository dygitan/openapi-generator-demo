import org.jetbrains.kotlin.gradle.dsl.JvmTarget

group = "github.tanpatrick"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configure<SourceSetContainer> {
	kotlin.sourceSets {
		named("main") {
			kotlin.srcDirs(
				"src/main/kotlin",
				"${project.layout.buildDirectory.get()}/generated/src/main/kotlin"
			)
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
	val kotlinVersion = "2.1.10"

	kotlin("jvm") version kotlinVersion

	kotlin("plugin.jpa") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion

	id("io.spring.dependency-management") version "1.0.11.RELEASE"

	id("org.openapi.generator") version "7.11.0"
	id("org.springframework.boot") version "3.4.0"
}

repositories {
	mavenCentral()
}

tasks.register("generateApiSpecsBackend", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
	generatorName.set("kotlin-spring")
	inputSpec.set("${File("$rootDir").parentFile}/openapi/specs.yaml")
	outputDir.set("${project.layout.buildDirectory.get()}/generated")

	apiPackage.set("github.tanpatrick.openapi.demo.generated.api")
	modelPackage.set("github.tanpatrick.openapi.demo.generated.model")
	packageName.set("github.tanpatrick.openapi.demo.generated")

	configOptions.set(mapOf(
		"documentationProvider" to "none",
		"exceptionHandler" to "false",
		"interfaceOnly" to "true",
		"useBeanValidation" to "false",
		"useJakartaEe" to "true",
		"useSpringBoot3" to "true",
		"useTags" to "true",
	))
}

tasks.register("generateApiSpecsFrontend", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
	generatorName.set("typescript-fetch")
	inputSpec.set("${File("$rootDir").parentFile}/openapi/specs.yaml")
	outputDir.set("${project.layout.buildDirectory.get()}/generated_frontend")

	configOptions.set(mapOf(
		"npmName" to "api-client",
		"npmVersion" to "1.0.0",
		"modelPropertyNaming" to "original",
		"enumPropertyNaming" to "original",
		"withInterfaces" to "true"
	))
}

tasks.compileKotlin {
	dependsOn("generateApiSpecsBackend")

	compilerOptions {
		freeCompilerArgs.add("-Xjsr305=strict")
		jvmTarget.set(JvmTarget.JVM_17)
	}
}

tasks.withType<Test> {
//	useJUnitPlatform()
}
