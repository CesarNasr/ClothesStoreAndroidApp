package com.example.clothesstoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.clothesstoreapp.ui.theme.ClothesStoreAppTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClothesStoreAppTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = {
                    BottomNavigationBar(
                        items = listOf(
                            BottomNavItem(
                                name = "Catalogue",
                                route = "catalogue",
                                icon = Icons.Default.Menu
                            ),
                            BottomNavItem(
                                name = "Wishlist",
                                route = "wishlist",
                                icon = Icons.Default.Favorite
                            ),
                            BottomNavItem(
                                name = "Basket",
                                route = "basket",
                                icon = Icons.Default.ShoppingCart
                            )
                        ), navController = navController, onItemClick = {
                            navController.navigate(it.route)
                        }
                    )
                }) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "catalogue") {
        composable("catalogue") {
            CatalogueScreen()
        }

        composable("wishlist") {
            WishlistScreen()
        }

        composable("basket") {
            BasketScreen()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(modifier, backgroundColor = Color.Gray, elevation = 5.dp) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(selected = selected,
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.White,
                onClick = { onItemClick(item) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0 && item.name == "Wishlist" ) {
                            BadgeBox(badgeContent = {
                                Text(text = item.badgeCount.toString())
                            }) {
                                Icon(imageVector = item.icon, contentDescription = item.name)
                            }
                        } else {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }

                        if (selected) {
                            Text(text = item.name, textAlign = TextAlign.Center, fontSize = 12.sp)
                        }
                    }
                }
            )
        }
    }

}


@Composable
fun CatalogueScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Catalogue")
    }
}


@Composable
fun WishlistScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Wishlist")
    }
}


@Composable
fun BasketScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Basket")
    }
}







//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ClothesStoreAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ClothesStoreAppTheme {
//        Greeting("Android")
//    }
//}