package com.example.words.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.words.db.model.Weeks
import com.example.words.screen.WeeksScreen
import com.example.words.screen.WorkScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen. getStartDestination()
    ) {
        composable(route = Screen.WeeksScreen.route) {
            WeeksScreen(navController = navController)
        }

        composable(route = Screen.WorkScreen.route) {
            WorkScreen(navController = navController)
        }
    }
}