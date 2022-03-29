plugins {
	application
}

val jnaVersion: String by rootProject.extra
val gdxVersion: String by rootProject.extra
val fastutil: String by rootProject.extra

dependencies {
	implementation(project(":api"))
	implementation(project(":core"))
	implementation(project(":scripts"))
	
	runtimeOnly(kotlin("script-runtime"))
	runtimeOnly(project(":userscripts"))
	
	implementation("net.java.dev.jna:jna:5.9.0")
	implementation("net.java.dev.jna:jna-platform:5.9.0")
	
	implementation("org.jire:kna:0.4.2")
	implementation("com.badlogicgames.gdx:gdx:1.10.0")
	implementation("com.badlogicgames.gdx:gdx-backend-lwjgl3:1.10.0")
	implementation("com.badlogicgames.gdx:gdx-platform:1.10.0:natives-desktop")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")

	implementation(fastutil)
	
	implementation("io.github.classgraph:classgraph:4.8.138")
}

application {
	mainClass.set("com.leagueofjire.app.LeagueOfJire")
	executableDir = "yogur"
	applicationDefaultJvmArgs = listOf("-Xmx8G","-Xms8G","-Xmn4G", "-client", "-XX:+UseParallelGC", "-XX:ParallelGCThreads=4", "-XX:-UseAdaptiveSizePolicy")
}