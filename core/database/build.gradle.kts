plugins {
    id("jun.android.library")
    id("yeen.android.hilt")
    id("yeen.android.room")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jun.database"
}

dependencies {
    implementation(projects.core.model)
}
