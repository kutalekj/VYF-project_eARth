package kutalekjk.earth

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.Manifest
import android.annotation.SuppressLint
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class LocationActivity : AppCompatActivity() {
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val tvLatitude : TextView = findViewById(R.id.tv_latitude)
        val tvLongitude : TextView = findViewById(R.id.tv_longitude)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                tvLatitude.text = location.latitude.toString()
                tvLongitude.text = location.longitude.toString()
            }

            override fun onProviderEnabled(provider: String) {
                // Called when the provider is enabled by the user.
            }

            override fun onProviderDisabled(provider: String) {
                // Called when the provider is disabled by the user.
            }
        }

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        }
    }

    /*
    Checks if the user has granted the permission that was requested by checking the "grantResults" array.
    If the array is not empty and the first element is "PackageManager.PERMISSION_GRANTED", then the permission has been granted.

    If the permission has been granted, the "ACCESS_FINE_LOCATION" permission is checked if still granted by checking "ContextCompat.checkSelfPermission()"
    (the user may have revoked the permission after granting it).

    If the "ACCESS_FINE_LOCATION" permission is still granted, the "locationManager.requestLocationUpdates()" is called to start receiving location updates.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
            }
        }
    }
}