package com.example.clothesstoreapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesstoreapp.R
import com.example.clothesstoreapp.datasource.model.Product
import com.example.clothesstoreapp.datasource.network.utils.Resource
import com.example.clothesstoreapp.datasource.repository.Repository
import com.example.clothesstoreapp.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
/**
 * View model holding the logic data of it's corresponding view (fragment / activity)
 */
@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val repository: Repository,
    @ApplicationContext private val applicationContext: Context,
) : ViewModel() {
    val uiState = MutableLiveData<UiState>()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        uiState.postValue(UiState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchProducts()
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            uiState.postValue(UiState.Loaded(data = it.products as MutableList<Product>))
                        }
                    }
                    is Resource.Error -> {
                        onErrorOccurred(false)
                    }

                    is Resource.Loading -> {
                        uiState.postValue(UiState.Loading)
                    }
                }
            } catch (e: Exception) {
                if (e is HttpException) {
                    onErrorOccurred(true)
                }
            }
        }
    }


    private fun onErrorOccurred(isInternetError: Boolean) {
        var errorMessage = applicationContext.getString(R.string.something_went_wrong)
        if (isInternetError) errorMessage =
            applicationContext.getString(R.string.check_internet_connection)

        uiState.postValue(UiState.Error(message = errorMessage))
    }

}