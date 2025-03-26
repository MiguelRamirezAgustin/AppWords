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



    companion object {
        fun getStartDestination() = HomeScreen.route
    }
}