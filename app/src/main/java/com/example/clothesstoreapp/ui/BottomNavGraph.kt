package com.example.clothesstoreapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clothesstoreapp.ui.screens.BasketScreen
import com.example.clothesstoreapp.ui.screens.CatalogueScreen
import com.example.clothesstoreapp.ui.screens.WishlistScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Catalogue.route
    ) {
        composable(route = BottomBarScreen.Catalogue.route) {
            CatalogueScreen()
        }
        composable(route = BottomBarScreen.Wishlist.route) {
            WishlistScreen()
        }
        composable(route = BottomBarScreen.Basket.route) {
            BasketScreen()
        }
    }
}