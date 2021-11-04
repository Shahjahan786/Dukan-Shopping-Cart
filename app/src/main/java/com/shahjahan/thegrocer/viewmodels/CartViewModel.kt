package com.shahjahan.thegrocer.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.shahjahan.thegrocer.App
import com.shahjahan.thegrocer.repository.MainRepository
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    val cartItemsRecView = repository.cartForAccount

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun removeAll() {
        viewModelScope.launch {
            _loading.value = true
            repository.deleteAll()
            _loading.value = false
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