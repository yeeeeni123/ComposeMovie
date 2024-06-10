plugins {
    id("jun.android.feature")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.yeen.ticketlist"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.immutable)
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation ("com.github.bumptech.glide:glide:4.11.0")
    implementation(libs.androidx.constraintlayout.compose)
    annotationProcessor ("com.github.bumptech.glide:compiler:4.11.0")
    implementation ("androidx.navigation:navigation-compose:2.5.2")

    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
}