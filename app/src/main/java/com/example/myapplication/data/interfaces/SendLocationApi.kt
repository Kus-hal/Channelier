package com.example.myapplication.data.interfaces

import androidx.lifecycle.LiveData
import com.example.myapplication.data.models.Customer

interface SendLocationApi {
    fun getLocationDetails(): LiveData<Customer>
    fun sendLocationUpdate(customer: Customer)
}
