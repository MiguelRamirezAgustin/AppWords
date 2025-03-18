package com.example.words.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen (val route:String, val argument: List<NamedNavArgument> = emptyList()){

    object WeeksScreen : Screen("WeeksScreen")
    object WorkScreen : Screen("WorkScreen")
    object ListChairs : Screen("ListChairs")
    object PaintScreen : Screen("PaintScreen")


    companion object {
        fun getStartDestination() = WeeksScreen.route
    }
}