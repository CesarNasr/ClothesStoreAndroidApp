package com.example.clothesstoreapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesstoreapp.R
import com.example.clothesstoreapp.datasource.model.Product
import com.example.clothesstoreapp.datasource.repository.Repository
import com.example.clothesstoreapp.datasource.network.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val repository: Repository,
    @ApplicationContext private val applicationContext: Context,

    ) : ViewModel() {
    val productList = MutableLiveData<MutableList<Product>>()

    private val _uiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState: LiveData<UiState> = _uiState
    val isLoading = MutableLiveData(true)

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        _uiState.postValue(UiState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchProducts()
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            isLoading.postValue(false)
                            _uiState.postValue(UiState.Loaded)
                            productList.postValue(it.products as MutableList<Product>)

                        }
                    }
                    is Resource.Error -> {
                        onErrorOccurred(false)
                    }

                    is Resource.Loading -> {
                        _uiState.postValue(UiState.Loading)
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

        _uiState.postValue(UiState.Error)
    }

}

enum class UiState {
    Empty,
    Loading,
    Loaded,
    Error
}