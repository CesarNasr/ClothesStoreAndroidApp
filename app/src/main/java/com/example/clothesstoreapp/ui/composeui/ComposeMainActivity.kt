package com.example.clothesstoreapp.ui.composeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.clothesstoreapp.ui.composeui.screens.MainScreen
import com.example.clothesstoreapp.ui.composeui.theme.ClothesStoreAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClothesStoreAppTheme {
                MainScreen()
            }
        }
    }
}