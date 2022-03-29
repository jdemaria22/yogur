pluginManagement {
	val kotlinVersion by extra("1.5.30")
	
	plugins {
		kotlin("jvm") version kotlinVersion
	}
	
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}
}

rootProject.name = "yogur"

include("api")
include("core")
include("app")
include("scripts")
include("userscripts")

