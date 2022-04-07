package com.example.clothesstoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.clothesstoreapp.ui.screens.MainScreen
import com.example.clothesstoreapp.ui.theme.ClothesStoreAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClothesStoreAppTheme {
                MainScreen()
            }
        }
    }
}