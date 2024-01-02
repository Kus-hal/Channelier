package com.example.myapplication.data.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.models.Customer
import javax.inject.Inject

class SendLocationApiImpl @Inject constructor() : SendLocationApi {

    private val locationDetailsLiveData = MutableLiveData<Customer>()

    override fun getLocationDetails(): LiveData<Customer> {
        return locationDetailsLiveData
    }

    override fun sendLocationUpdate(customer: Customer) {
        locationDetailsLiveData.postValue(customer)
        // Implement your logic to send location updates to the server if needed
    }
}
