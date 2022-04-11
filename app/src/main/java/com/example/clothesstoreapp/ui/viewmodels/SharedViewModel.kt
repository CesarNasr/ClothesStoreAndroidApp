package com.example.clothesstoreapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clothesstoreapp.datasource.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val addToWishList = MutableLiveData(Unit)
    val addToBasket = MutableLiveData(Unit)


    fun addToWishList(){

    }

    fun addToBasket(){


    }
}