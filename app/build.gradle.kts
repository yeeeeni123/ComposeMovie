plugins {
    id("jun.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yeen.movieapp"

    defaultConfig {
        applicationId = "com.yeen.movieapp"
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {
    implementation(projects.feature.main)
    implementation(projects.core.designsystem)
    implementation(libs.firebase.perf.ktx)
}