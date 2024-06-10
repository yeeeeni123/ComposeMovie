plugins {
    id("jun.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yeen.ticketiong"
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

    //Coil
    implementation ("io.coil-kt:coil-compose:2.2.0")
}