package com.example.yaganaste.navigation

sealed class AppScreens(val route: String) {
    object BancsList: AppScreens("bancs_lst_scn")
    object BancsDetail: AppScreens("bancs_detail_scn")
}