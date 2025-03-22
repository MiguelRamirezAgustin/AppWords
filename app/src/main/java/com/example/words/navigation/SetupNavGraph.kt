package com.example.words.navigation

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.words.Model.ChairsViewModel
import com.example.words.Model.PaintViewModel
import com.example.words.db.model.Weeks
import com.example.words.screen.HomeScreen
import com.example.words.screen.ListChairs
import com.example.words.screen.ListPaint
import com.example.words.screen.PaintScreen
import com.example.words.screen.WeeksScreen
import com.example.words.screen.WorkScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.getStartDestination()
    ) {
        composable(route = Screen.WeeksScreen.route) {
            WeeksScreen(navController = navController)
        }

        composable(route = Screen.WorkScreen.route) {
            WorkScreen(
                navController = navController,
                viewModel = ChairsViewModel(Application())
            )
        }

        composable(route = Screen.ListChairs.route) {
            ListChairs(
                navController = navController,
                viewModel = ChairsViewModel(Application())
            )
        }

        composable(route = Screen.PaintScreen.route) {
            PaintScreen(
                navController = navController,
                viewModel = PaintViewModel(Application())
            )
        }

        composable(route = Screen.ListPaint.route) {
            ListPaint(
                navController = navController,
                viewModel = PaintViewModel(Application())
            )
        }

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                navController = navController,
            )
        }




    }
}


