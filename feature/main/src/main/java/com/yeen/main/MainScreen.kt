package com.yeen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ticketlist.TicketListScreen
import com.yeen.main.bottomnav.BottomNavItem
import com.yeen.main.bottomnav.MyPage
import com.yeen.main.bottomnav.Ticketing
import com.yeen.main.drawer.MenuItem
import com.yeen.main.drawer.ScaffoldState
import com.yeen.main.home.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    onLoginClick: (Int) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Box(Modifier.padding(it)){
            NavigationGraph(navController = navController)
        }
    }

}


@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Ticketing,
        BottomNavItem.TicketList,
        BottomNavItem.MyPage
    )

    NavigationBar(containerColor = colorResource(id = R.color.brightRed1)) {
        items.forEach { item ->
            AddItem(
                screen = item, navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBarItem(
        // Text that shows bellow the icon
        label = {
            Text(text = screen.title)
        },

        // The icon resource
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = screen.title
            )
        },

        // Display if the icon it is select or not
        // Always show the label bellow the icon or not
        alwaysShowLabel = true,
        selected = currentRoute == screen.screen_route,


        // Click listener for the icon
        onClick = {
            navController.navigate(screen.screen_route) {
                navController.graph.startDestinationRoute?.let {
                    popUpTo(it) { saveState = true }
                }
                launchSingleTop = true
                restoreState = true
            }
        },

        // Control all the colors of the icon
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = colorResource(id = R.color.brightRed),
            unselectedIconColor = colorResource(id = R.color.brightRed3),
            indicatorColor = colorResource(id = R.color.brightRed2)
        )

    )
}


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.Ticketing.screen_route) {
            Ticketing()
        }
        composable(BottomNavItem.TicketList.screen_route) {
            TicketListScreen()
        }
        composable(BottomNavItem.MyPage.screen_route) {
            MyPage()
        }
    }
}


@Composable
fun DrawerItem(menuItem: MenuItem, modifier: Modifier = Modifier, onItemClick: (MenuItem) -> Unit) {
    Column(
        modifier = Modifier.clickable {
            onItemClick(menuItem)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(id = menuItem.textId),
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
        Divider()
    }
}


@Composable
fun DrawerBody(
    menuItems: List<MenuItem>,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(menuItems) { item ->
            DrawerItem(
                item,
                modifier = modifier
            ) {
                scope.launch {
                    scaffoldState
                }
                onItemClick(item)
            }
        }
    }
}



