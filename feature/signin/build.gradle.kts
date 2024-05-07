plugins {
    id("jun.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yeen.signin"
}
dependencies {
    implementation(project(":feature:login"))
}
