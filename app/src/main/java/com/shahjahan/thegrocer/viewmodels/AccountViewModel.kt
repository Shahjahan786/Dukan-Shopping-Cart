package com.shahjahan.thegrocer.viewmodels
import androidx.lifecycle.*
import com.shahjahan.thegrocer.repository.MainRepository

class AccountViewModel(repository: MainRepository) : ViewModel() {

    val orders = repository.orders
}