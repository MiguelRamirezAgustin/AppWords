package com.example.words.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen (val route:String, val argument: List<NamedNavArgument> = emptyList()){

    object HomeScreen : Screen("HomeScreen")
    object LaborDayScreen : Screen("LaborDayScreen")
    object WorkScreen : Screen("WorkScreen")
    object ListChairs : Screen("ListChairs")
    object ListPaint : Screen("ListPaint")
    object PaintScreen : Screen("PaintScreen")
    object ListLaborDay:Screen("ListLaborDay")
    object ChairsTejidoScreen:Screen("ChairsTejidoScreen")
    object ListChairsTedijo:Screen("ListChairsTedijo")
    object ChairHistory:Screen("ChairHistory")
    object ChairQuote:Screen("ChairQuote")
    object MaterialPriceScreen:Screen("MaterialPriceScreen")
    companion object {
        fun getStartDestination() = HomeScreen.route
    }
}