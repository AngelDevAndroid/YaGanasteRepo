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

        composable(AppScreens.BancsDetail.route + "/{img_game}/{desc_name}",
            arguments = listOf(
                navArgument(name = "img_game") {
                    type = NavType.StringType
                },
                navArgument("desc_name") {
                    type = NavType.StringType
                    defaultValue = "null"
                }
            )
        ) { backStackEntry ->
            val image = backStackEntry.arguments?.getString("img_game") ?: ""
            val desc = backStackEntry.arguments?.getString("desc_name") ?: ""
            BancsDetail(image, desc)
        }
    }
}

