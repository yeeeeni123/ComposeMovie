plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "yeen.android.hilt"
            implementationClass = "com.yeen.movieApp.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "yeen.kotlin.hilt"
            implementationClass = "com.yeen.movieApp.HiltKotlinPlugin"
        }
        register("androidRoom") {
            id = "yeen.android.room"
            implementationClass = "com.yeen.movieApp.AndroidRoomPlugin"
        }

    }
}
