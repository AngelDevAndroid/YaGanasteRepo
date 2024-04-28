package com.example.yaganaste.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yaganaste.ui_views.BancsDetail
import com.example.yaganaste.ui_views.BancsList

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.BancsList.route) {

        composable(AppScreens.BancsList.route) {
            BancsList(navController)
        }

        composable(AppScreens.BancsDetail.route + "/{namebanc}/{agebanc}/{descbanc}",
            arguments = listOf(
                navArgument(name = "namebanc") {
                    type = NavType.StringType
                },
                navArgument("agebanc") {
                    type = NavType.StringType
                    defaultValue = "null"
                },
                navArgument("descbanc") {
                    type = NavType.StringType
                    defaultValue = "null"
                }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("namebanc") ?: ""
            val age = backStackEntry.arguments?.getString("agebanc") ?: ""
            val desc = backStackEntry.arguments?.getString("descbanc") ?: ""
            BancsDetail(name, age, desc)
        }
    }
}

