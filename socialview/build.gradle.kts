plugins {
    android("library")
    kotlin("android")
    bintray
    `bintray-release`
}

android {
    compileSdkVersion(SDK_TARGET)
    defaultConfig {
        minSdkVersion(SDK_MIN)
        targetSdkVersion(SDK_TARGET)
        versionName = RELEASE_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("AndroidManifest.xml")
            java.srcDirs("src")
            res.srcDir("res")
            resources.srcDir("src")
        }
        getByName("androidTest") {
            setRoot("tests")
            manifest.srcFile("tests/AndroidManifest.xml")
            java.srcDir("tests/src")
            res.srcDir("tests/res")
            resources.srcDir("tests/src")
        }
    }
    lintOptions {
        isCheckTestSources = true
    }
    libraryVariants.all {
        generateBuildConfigProvider?.invoke {
            enabled = false
        }
    }
}

dependencies {
    implementation(androidx("appcompat"))

    androidTestImplementation(kotlin("stdlib", VERSION_KOTLIN))
    androidTestImplementation(kotlin("test-junit", VERSION_KOTLIN))
    androidTestImplementation(material())
    androidTestImplementation(androidx("test.espresso", "espresso-core", VERSION_ESPRESSO))
    androidTestImplementation(androidx("test", "runner", VERSION_RUNNER))
    androidTestImplementation(androidx("test", "rules", VERSION_RULES))
}

publish {
    bintrayUser = BINTRAY_USER
    bintrayKey = BINTRAY_KEY
    dryRun = false
    repoName = RELEASE_REPO

    userOrg = RELEASE_USER
    groupId = RELEASE_GROUP
    artifactId = RELEASE_ARTIFACT
    publishVersion = RELEASE_VERSION
    desc = RELEASE_DESC
    website = RELEASE_WEBSITE
}
