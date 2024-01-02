package com.example.myapplication.data.models

data class Customer(
    val name: String,
    val contactNo: String,
    val customerId: String,
    val location: String
) {
    companion object {
        fun createCustomer(name: String, contactNo: String, customerId: String, location: String): Customer {
            return  Customer(
                name = name,
                contactNo = contactNo,
                customerId = customerId,
                location = location
            )
        }
    }
}
