plugins {
    id("jun.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yeen.login"
}


dependencies {
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.1")
    implementation("com.google.firebase:firebase-firestore-ktx:24.10.1")
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.dagger:hilt-android:2.44")
    implementation ("com.google.firebase:firebase-auth-ktx")
}
