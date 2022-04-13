package com.example.clothesstoreapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesstoreapp.R
import com.example.clothesstoreapp.datasource.model.Product
import com.example.clothesstoreapp.datasource.repository.Repository
import com.example.clothesstoreapp.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * View model holding the logic data of it's corresponding view (fragment / activity)
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: Repository,
    @ApplicationContext private val applicationContext: Context,

    ) : ViewModel() {

    val onItemInserted = MutableLiveData<UiState>(UiState.Empty)

    fun addToWishList(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertWishlistItem(product)
                onItemInserted.postValue(UiState.Loaded(message = applicationContext.resources.getString(R.string.added_to_wishlist)))
            } catch (e: Exception) {
                onItemInserted.postValue(UiState.Error(applicationContext.resources.getString(R.string.something_went_wrong)))
            }

        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertBasketItem(product)
                onItemInserted.postValue(UiState.Loaded(message = applicationContext.resources.getString(R.string.item_added_cart)))
            } catch (e: Exception) {
                onItemInserted.postValue(UiState.Error(applicationContext.resources.getString(R.string.something_went_wrong)))
            }
        }
    }
}