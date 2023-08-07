pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven("https://plugins.gradle.org/m2/")
        maven ( "https://jitpack.io")

        mavenCentral( )
    }
}

rootProject.name = "Milk Revenue Tracker"
include(":app")
 