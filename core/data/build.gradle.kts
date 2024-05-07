plugins {
    id("jun.android.library")
    id("yeen.android.hilt")
    id("yeen.kotlin.hilt")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
}

android {
    namespace = "com.jun.data"
}
dependencies {
    implementation(projects.core.model)
    implementation(projects.core.database)
    // ktor 네트워크
    implementation ("io.ktor:ktor-client-core:1.6.3")
    implementation ("io.ktor:ktor-client-serialization:1.6.3")
    implementation ("io.ktor:ktor-client-logging:1.6.3")
    implementation ("io.ktor:ktor-client-gson:1.6.3")
    implementation ("io.ktor:ktor-client-android:1.6.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    implementation ("com.squareup.retrofit2:retrofit:2.4.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.0.0")
}