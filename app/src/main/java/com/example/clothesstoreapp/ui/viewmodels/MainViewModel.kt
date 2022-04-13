package com.example.clothesstoreapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesstoreapp.datasource.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * View model holding the logic data of it's corresponding view (fragment / activity)
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val wishListCount = MutableLiveData<Int>()
    val basketCount = MutableLiveData<Int>()

    init {
        getWishListCount()
        getBasketCount()
    }

    private fun getWishListCount() {
        viewModelScope.launch {
            repository.getWishListCount().collect {
                wishListCount.value = it
            }
        }
    }

    private fun getBasketCount() {
        viewModelScope.launch {
            repository.getBasketCount().collect {
                basketCount.value = it
            }
        }
    }
}