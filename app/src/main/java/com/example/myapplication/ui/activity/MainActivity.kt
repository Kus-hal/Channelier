package com.example.myapplication.ui.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.myapplication.data.models.Customer
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SubmitBtn.setOnClickListener {

            val intent = Intent(this, Final::class.java)
            intent.putExtra("name", binding.NameEt.text.toString())
            intent.putExtra("contactNumber", binding.ContactEt.text.toString())
            intent.putExtra("customerId", binding.CustIdET.text.toString())
            startActivity(intent)
        }
    }
}
