plugins {
    id("jun.android.library")
    id("jun.android.compose")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jun.designsystem"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation("androidx.compose.material:material:1.0.1")
}