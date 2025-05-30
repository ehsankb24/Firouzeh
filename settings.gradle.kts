pluginManagement {
    repositories {
        maven(url = "https://maven.myket.ir")
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://maven.myket.ir")
        google()
        mavenCentral()
    }
}

rootProject.name = "Firouzeh"
include(":app")
include(":domain")
include(":ui")
include(":news")
include(":data")
include(":di")