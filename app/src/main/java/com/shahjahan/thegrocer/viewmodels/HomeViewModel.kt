package com.shahjahan.thegrocer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahjahan.thegrocer.db.entities.Product
import com.shahjahan.thegrocer.repository.MainRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainRepository) : ViewModel() {

    //For loading screen => progress bar
    enum class ApiStatus { LOADING, DONE, ERROR }

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    //repository
    val categories = repository.categories
    val products = repository.products
    val cartItems = repository.cartForAccount

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                repository.refreshKitsAndItems()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    //onClicks
    fun addToCart(id: Int) {
        viewModelScope.launch {
            repository.add(id)
        }
    }

    fun removeFromCart(id: Int) {
        viewModelScope.launch {
            repository.remove(id)
        }
    }

    suspend fun getProductById(id: Int): LiveData<Product> {

        return repository.getProductById(id)

    }
}