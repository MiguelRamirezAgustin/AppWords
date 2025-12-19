package com.example.words.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.words.screen.ChairHistory
import com.example.words.screen.ChairQuote
import com.example.words.screen.ChairsTejidoScreen
import com.example.words.screen.HomeScreen
import com.example.words.screen.LaborDayScreen
import com.example.words.screen.ListChairs
import com.example.words.screen.ListChairsTedijo
import com.example.words.screen.ListLaborDay
import com.example.words.screen.ListPaint
import com.example.words.screen.MaterialPriceScreen
import com.example.words.screen.NewNoteScreen
import com.example.words.screen.PaintScreen
import com.example.words.screen.WorkScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.getStartDestination()
    ) {
        composable(route = Screen.LaborDayScreen.route) {
          LaborDayScreen(navController = navController)
        }

        composable(route = Screen.WorkScreen.route) {
            WorkScreen(
                navController = navController
            )
        }

        composable(route = Screen.ListChairs.route) {
            ListChairs(
                navController = navController
            )
        }

        composable(route = Screen.PaintScreen.route) {
            PaintScreen(
                navController = navController
            )
        }

        composable(route = Screen.ListPaint.route) {
            ListPaint(
                navController = navController
            )
        }

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                navController = navController,
            )
        }


        composable(route = Screen.ListLaborDay.route) {
            ListLaborDay(
                navController = navController
            )
        }

        composable(route = Screen.ChairsTejidoScreen.route) {
            ChairsTejidoScreen(
                navController = navController
            )
        }

        composable(route = Screen.ListChairsTedijo.route) {
            ListChairsTedijo(
                navController = navController
            )
        }

        composable(route = Screen.ChairHistory.route) {
            ChairHistory(
                navController = navController
            )
        }

        composable(route = Screen.ChairQuote.route) {
            ChairQuote(
                navController = navController
            )
        }

        composable(route = Screen.MaterialPriceScreen.route) {
            MaterialPriceScreen(
                navController = navController
            )
        }

        composable(route = Screen.NewNoteScreen.route) {
            NewNoteScreen(
                navController = navController
            )
        }






    }
}


