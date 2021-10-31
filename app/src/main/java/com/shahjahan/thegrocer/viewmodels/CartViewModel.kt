package com.shahjahan.thegrocer.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shahjahan.thegrocer.repository.MainRepository
import kotlinx.coroutines.launch

class CartViewModel(
    application: Application,
    private val repository: MainRepository
) : AndroidViewModel(application) {

    val cartItemsRecView = repository.cartForAccount

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun removeAll() {
        viewModelScope.launch {
            _loading.value = true
            repository.deleteAll()
            _loading.value = false
            Toast.makeText(getApplication(), "Paid", Toast.LENGTH_LONG).show()
        }
    }

    fun remove(id:Int) {
        viewModelScope.launch {
            _loading.value = true
            repository.remove(id)
            _loading.value = false

        }
    }
}