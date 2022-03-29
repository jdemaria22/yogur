
val gdxVersion: String by rootProject.extra
val fastutil: String by rootProject.extra

dependencies {
	compileOnly(project(":api"))
	compileOnly(project(":core"))
	compileOnly(kotlin("scripting-common"))

	compileOnly("com.badlogicgames.gdx:gdx:1.10.0")
	compileOnly(fastutil)
	val jacksonVersion = "2.12.4"
	implementation ("com.google.code.gson:gson:2.8.8")
	implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
}