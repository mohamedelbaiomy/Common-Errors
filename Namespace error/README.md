الحل هو انك تنسخ الجزء ده :

subprojects {
    project.buildDir = "${rootProject.buildDir}/${project.name}"
    afterEvaluate {
        project -> if (project.hasProperty('android')) {
            project.android {
                if(namespace == null) {
                    namespace project.group
                }
            }
        }
    }
}

وتضيفه للـ build.gradle - موجود نفس الحل بردو بالـ syntax الجديد اللى هو build.gralde.kts
