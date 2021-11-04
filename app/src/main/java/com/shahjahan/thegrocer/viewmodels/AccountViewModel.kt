package com.shahjahan.thegrocer.viewmodels
import androidx.lifecycle.*
import com.shahjahan.thegrocer.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(repository: MainRepository) : ViewModel() {

    val orders = repository.orders
}