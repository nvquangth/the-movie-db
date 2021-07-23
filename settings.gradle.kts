rootProject.name = "CleanArchitecture"
include(":base", ":entity", ":domain", ":data", ":app", ":common", ":dynamicfeature")
project(":dynamicfeature").projectDir = File(rootDir, "features/dynamicfeature")