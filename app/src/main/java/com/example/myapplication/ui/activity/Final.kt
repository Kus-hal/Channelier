package com.example.myapplication.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.myapplication.databinding.ActivityFinalBinding
class Final : AppCompatActivity() {

    private lateinit var binding: ActivityFinalBinding
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    // Add this constant at the top of your activity or in the companion object
    private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 10f  // 10 meters
    private val MIN_TIME_BETWEEN_UPDATES: Long = 1000  // 1 second

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startLocationUpdates()
            } else {
                Toast.makeText(
                    this,
                    "Location permission denied. Please enable it for the app to work.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from intent
        val name = intent.getStringExtra("name") ?: ""
        val contactNumber = intent.getStringExtra("contactNumber") ?: ""
        val customerId = intent.getStringExtra("customerId") ?: ""

        // Update TextViews with user-entered data
        binding.nameTextView.text = name
        binding.contactTextView.text = contactNumber
        binding.customerIdTextView.text = customerId

        // Initialize location-related components
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                // Update UI with new location data
                updateLocationUI(location)
            }

            override fun onProviderDisabled(provider: String) {
                // Handle provider disabled
            }

            override fun onProviderEnabled(provider: String) {
                // Handle provider enabled
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                // Handle status changed
            }
        }

        // Check and request location permissions
        checkAndRequestLocationPermissions()
    }

    private fun checkAndRequestLocationPermissions() {
        val fineLocationPermission =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLocationPermission =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (fineLocationPermission != PackageManager.PERMISSION_GRANTED ||
            coarseLocationPermission != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            var location: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            var latitute: String = location?.latitude.toString()
            var longitude: String = location?.longitude.toString()
            binding.locationTextView.text = latitute+","+longitude
            startLocationUpdates()
        }
    }

    private fun startLocationUpdates() {
        // Check for permission again before requesting updates
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_TIME_BETWEEN_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                locationListener
            )
        }
        var location: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        var latitute: String = location?.latitude.toString()
        var longitude: String = location?.longitude.toString()
        binding.locationTextView.text = latitute+","+longitude

            // Start the foreground service for location tracking
        startLocationTrackingService()
    }

    private fun updateLocationUI(location: Location) {
        // Update TextView with location data

        binding.locationTextView.text = location.latitude.toString()+","+location.longitude.toString()
        Toast.makeText(this, "Location changed", Toast.LENGTH_SHORT).show()
    }

    private fun startLocationTrackingService() {
        val serviceIntent = Intent(this, Final::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Check for permission before removing updates
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.removeUpdates(locationListener)
        }
    }
}
