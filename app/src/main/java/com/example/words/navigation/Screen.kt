package com.example.words.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen (val route:String, val argument: List<NamedNavArgument> = emptyList()){

    object WeeksScreen : Screen("WeeksScreen")
    object WorkScreen : Screen("WorkScreen")

    companion object {
        fun getStartDestination() = WeeksScreen.route
    }
}