package com.example.clothesstoreapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.clothesstoreapp.R
import com.example.clothesstoreapp.databinding.ActivityMainBinding
import com.example.clothesstoreapp.ui.viewmodels.SharedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedViewModel: SharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        setupNavController()
    }


    private fun setupNavController() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottomNavBar)
        navView.setupWithNavController(navController)


        // todo put in observable

        val menuItemId: Int = navView.menu.getItem(1).itemId //0 menu item index.

        val badge = navView.getOrCreateBadge(menuItemId)
        badge.isVisible = true
        badge.number = 99

//        badge.isVisible = false   //hide badge
//        badge.clearNumber()
        //bottomNavigation.removeBadge(menuItemId)
    }
}