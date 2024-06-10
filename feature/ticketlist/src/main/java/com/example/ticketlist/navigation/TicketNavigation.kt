package com.example.ticketlist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavController.navigateTicketList() {
    navigate(TicketListRoute.route)
}

fun NavGraphBuilder.mainNavGraph(
    onMainClick: (Int) -> Unit
) {
    composable(route = TicketListRoute.route) {
//        TicketListScreen()
    }
}

object TicketListRoute {
    const val route = "ticketlist"
}