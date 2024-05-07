package com.yeen.main.bottomnav
import com.yeen.main.R


sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object Ticketing: BottomNavItem("Ticketing", R.drawable.ic_ticketing,"ticketing")
    object TicketList: BottomNavItem("TicketList", R.drawable.ticket_list,"ticket_list")
    object MyPage: BottomNavItem("MyPage", R.drawable.my_page,"my_page")
}
