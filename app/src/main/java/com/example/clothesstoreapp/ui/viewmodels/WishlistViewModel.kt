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
class WishlistViewModel @Inject constructor(
    private val repository: Repository,
    @ApplicationContext private val applicationContext: Context,
    ) : ViewModel() {

    val fetchWishListUiState = MutableLiveData<UiState>(UiState.Empty)
    val addToBasketUiState = MutableLiveData<UiState>(UiState.Empty)

    val showEmptyView = MutableLiveData(false)



    fun fetchWishListProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.fetchWishlistItems()
                toggleEmptyView(result.isNullOrEmpty())

                fetchWishListUiState.postValue(UiState.Loaded(data = result))
            } catch (e: Exception) {
                fetchWishListUiState.postValue(UiState.Error(applicationContext.resources.getString(
                    R.string.something_went_wrong)))
            }
        }
    }


    fun addItemToBasket(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertBasketItem(product)
                repository.deleteWishlistItem(product)

                addToBasketUiState.postValue(UiState.Loaded(product))
            } catch (e: Exception) {
                addItemToBasket(product)
                addToBasketUiState.postValue(UiState.Error())
            }
        }
    }

    fun removeItemFromWishlist(product: Product, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteWishlistItem(product)


                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess.invoke()
                }
            } catch (e: Exception) {
                removeItemFromWishlist(product, onSuccess)
            }
        }
    }

     fun toggleEmptyView(visible: Boolean) {
        showEmptyView.postValue(visible)
    }
}