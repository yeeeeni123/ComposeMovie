package com.yeen.main.drawer

import com.yeen.main.R

data class MenuItem(
    val id: ScreensRoute,
    val textId: Int
)

val drawerScreens = listOf(
    MenuItem(ScreensRoute.SCREEN_1, R.string.screen_1),
    MenuItem(ScreensRoute.SCREEN_2, R.string.screen_2),
    MenuItem(ScreensRoute.SCREEN_3, R.string.screen_3),
)

enum class ScreensRoute {
    SCREEN_1, SCREEN_2, SCREEN_3
}
