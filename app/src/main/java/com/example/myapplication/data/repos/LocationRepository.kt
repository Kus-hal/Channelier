package com.example.myapplication.data.repos

import com.example.myapplication.data.interfaces.SendLocationApi
import com.example.myapplication.data.models.Customer
import javax.inject.Inject

class LocationRepository @Inject constructor(private val sendLocationApi: SendLocationApi) {

    fun getLocationDetails() = sendLocationApi.getLocationDetails()

    fun sendLocationUpdate(customer: Customer) {
        sendLocationApi.sendLocationUpdate(customer)
    }
}
