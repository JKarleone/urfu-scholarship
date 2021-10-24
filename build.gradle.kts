buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    }
}

tasks {
    @Suppress("UNUSED_VARIABLE")
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
