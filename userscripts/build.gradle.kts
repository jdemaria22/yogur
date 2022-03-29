val gdxVersion: String by rootProject.extra
val fastutil: String by rootProject.extra

dependencies {
	compileOnly(project(":api"))
	compileOnly(project(":scripts"))
	compileOnly(project(":core"))

	compileOnly("com.badlogicgames.gdx:gdx:1.10.0")
	compileOnly(fastutil)
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.6.0")
}

sourceSets.main {
	java.srcDirs("scripts")
}