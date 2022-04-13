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
class BasketViewModel @Inject constructor(
    private val repository: Repository,
    @ApplicationContext private val applicationContext: Context,

    ) : ViewModel() {
    val basketUiState = MutableLiveData<UiState>(UiState.Empty)
    val totalPrice = MutableLiveData("")
    val showEmptyView = MutableLiveData(false)

    fun fetchBasketProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.fetchBasketItems()
                calculateTotal(result)
                toggleEmptyView(result.isNullOrEmpty())
                basketUiState.postValue(UiState.Loaded(data = result))
            } catch (e: Exception) {
                basketUiState.postValue(UiState.Error(applicationContext.resources.getString(R.string.something_went_wrong)))
            }
        }
    }

    fun deleteBasketProducts(productId: Int, onSuccess: (List<Product>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.deleteBasketItemAndFetch(productId)
                calculateTotal(result)
                toggleEmptyView(result.isNullOrEmpty())
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess(result)
                }
            } catch (e: Exception) {
                deleteBasketProducts(productId, onSuccess)
            }
        }
    }


    private fun calculateTotal(items: List<Product>) {
        var total = 0.0
        for (item in items) {
            total += (item.price * (item.qty ?: 1))
        }
        totalPrice.postValue("$$total")
    }

    private fun toggleEmptyView(visible: Boolean) {
        showEmptyView.postValue(visible)
    }

}