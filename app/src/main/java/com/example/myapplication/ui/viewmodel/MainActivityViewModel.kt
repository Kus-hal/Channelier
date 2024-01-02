package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.models.Customer
import com.example.myapplication.data.repos.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val locationRepo: LocationRepository) : ViewModel() {

    val locationDetails: LiveData<Customer> = locationRepo.getLocationDetails()

    fun sendLocationUpdate(customer: Customer) {
        locationRepo.sendLocationUpdate(customer)
    }
}
