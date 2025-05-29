allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.set(newBuildDir)

subprojects {
    val subprojectBuildDir = newBuildDir.dir(project.name)
    project.layout.buildDirectory.set(subprojectBuildDir)

    evaluationDependsOn(":app")

    val androidConfiguration: Action<Project> = Action {
        if (extensions.findByName("android") != null) {
            extensions.configure<com.android.build.gradle.BaseExtension>("android") {
                if (namespace == null) {
                    namespace = group.toString()
                }
            }
        }
    }

    if (state.executed) {
        androidConfiguration.execute(this)
    } else {
        afterEvaluate(androidConfiguration)
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}