package com.ahs.property

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ahs.property.databinding.ActivityMapsBinding
import com.ahs.property.property.Brochers
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMapClickListener{

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var projectType: String
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var REQUEST_CODE = 101
    private lateinit var supportMapFragment:SupportMapFragment
    private lateinit var latLng : LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportMapFragment = (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        binding.toolbar.incToolbarImage.visibility = View.VISIBLE
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()

        val intent = getIntent()
        projectType = intent.getStringExtra("type").toString()

        if(projectType.equals("casacanal")){
            binding.buttonShowDropDown.setImageResource(R.drawable.casacanal_01)
            binding.textLocationus.setText("Dubai water canal adjacent to safa park Al Safa 2 - Dubai")

        }else if(projectType.equals("onecanal")){
            binding.textLocationus.setText("Dubai water canal adjacent to safa park - Al Safa 2 - Dubai")
            binding.buttonShowDropDown.setImageResource(R.drawable.onecanal_01)

        }else if(projectType.equals("onecresecent")){
            binding.textLocationus.setText("Palm Jumeirah Walk Way Palm - Crescent Rd - Jumeirah - Dubai")
            binding.buttonShowDropDown.setImageResource(R.drawable.onecrescent_01)

        }else if(projectType.equals("searenity")){
            binding.textLocationus.setText("K70 - Palm Jumeirah Rd - The Palm Jumeirah - Dubai")
            binding.toolbar.aboutus.setText("Villas")
            binding.buttonShowDropDown.setImageResource(R.drawable.searenity_02)

        }else if(projectType.equals("amara")){
            binding.textLocationus.setText("35C8+2XP L22 - Emirates Hills - Dubai")
            binding.toolbar.aboutus.setText("Villas")
            binding.buttonShowDropDown.setImageResource(R.drawable.amara_02)

        }else if(projectType.equals("sunrays")){
            binding.textLocationus.setText("C128 - Palm Jumeirah Rd - The Palm Jumeirah - Dubai")
            binding.toolbar.aboutus.setText("Villas")
            binding.buttonShowDropDown.setImageResource(R.drawable.sunrays_01)

        }else if(projectType.equals("serene")){
            binding.textLocationus.setText("K73 - Palm Jumeirah Rd - The Palm Jumeirah - Dubai")
            binding.toolbar.aboutus.setText("Villas")
            binding.buttonShowDropDown.setImageResource(R.drawable.serene_01)

        }else if(projectType.equals("azalea")){
            binding.textLocationus.setText("N49 - Palm Jumeirah Rd - The Palm Jumeirah - Dubai")
            binding.toolbar.aboutus.setText("Villas")
            binding.buttonShowDropDown.setImageResource(R.drawable.azalea_01)
        }

        binding.btnLogout.setOnClickListener(View.OnClickListener { view ->

            if(projectType.equals("casacanal")){
                val strUri =
                    "https://goo.gl/maps/vMfZ9dYCu3ZmHjXQ8"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
                intent.setClassName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"
                )
                startActivity(intent)

            }else if(projectType.equals("onecanal")){
                val strUri =
                    "https://goo.gl/maps/GpfUeLghLoazMquA6"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
                intent.setClassName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"
                )
                startActivity(intent)

            }else if(projectType.equals("onecresecent")){
                val strUri =
                    "https://goo.gl/maps/CdLT53vUyujd63wF9"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
                intent.setClassName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"
                )
                startActivity(intent)

            }else if(projectType.equals("searenity")){
                val strUri =
                    "https://goo.gl/maps/HbhsZqQcAibJ1q9MA"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
                intent.setClassName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"
                )
                startActivity(intent)

            }else if(projectType.equals("amara")){
                val strUri =
                    "https://goo.gl/maps/jnLcwxopCDKU8G6Z9"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
                intent.setClassName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"
                )
                startActivity(intent)

            }else if(projectType.equals("sunrays")){
                val strUri =
                    "https://goo.gl/maps/GdJk65fVhWY8kzgGA"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
                intent.setClassName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"
                )
                startActivity(intent)

            }else if(projectType.equals("serene")){
                val strUri =
                    "https://goo.gl/maps/1KMTP42TKEk9eDcn9"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
                intent.setClassName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"
                )
                startActivity(intent)

            }else if(projectType.equals("azalea")){
                val strUri =
                    "https://goo.gl/maps/jMyhvVb1ktqqoBk57"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
                intent.setClassName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"
                )
                startActivity(intent)
            }
        })

        binding.toolbar.incToolbarImage.setOnClickListener { this@MapsActivity.onBackPressedDispatcher.onBackPressed() }
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE
            )
            return
        }
        val task: Task<Location> = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener(OnSuccessListener<Location> { location ->
            if (location != null) {
                currentLocation = location
               /* Toast.makeText(
                    applicationContext,
                    currentLocation.latitude.toString() + "" + currentLocation.longitude,
                    Toast.LENGTH_SHORT
                ).show()*/

                supportMapFragment.getMapAsync(this@MapsActivity)
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        googleMap.setOnMapClickListener(this)
        //val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        if(projectType.equals("casacanal")){
            latLng = LatLng(25.188108, 55.244958)

        }else if(projectType.equals("onecanal")){
            latLng = LatLng(25.188535, 55.243937)

        }else if(projectType.equals("onecresecent")){
            latLng = LatLng(25.139803, 55.142883)

        }else if(projectType.equals("searenity")){
            latLng = LatLng(25.1157758, 55.1167395)

        }else if(projectType.equals("amara")){
            latLng = LatLng(25.0700799, 55.1668243)

        }else if(projectType.equals("sunrays")){
            latLng = LatLng(25.127511, 55.144334)

        }else if(projectType.equals("serene")){
            latLng = LatLng(25.1159072, 55.1172104)

        }else if(projectType.equals("azalea")){
           latLng = LatLng(25.113024, 55.128053)
        }

        val markerOptions = MarkerOptions().position(latLng)
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        googleMap.addMarker(markerOptions)
      /*  val sydney = LatLng(25.095, 55.172)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in CASA CANAL"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        val zoomLevel = 20f //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //buildGoogleApiClient()
                mMap!!.isMyLocationEnabled = true
                // val sydney = LatLng(25.095, 55.172)
                // mMap!!.addMarker(MarkerOptions().position(sydney).title("Marker in CASA CANAL"))
                // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
                // val zoomLevel = 16.0f //This goes up to 21
                // mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))
            }
        } else {
          //  buildGoogleApiClient()
            mMap!!.isMyLocationEnabled = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onMapClick(p0: LatLng) {

        if(projectType.equals("casacanal")){
            val strUri =
                "https://goo.gl/maps/vMfZ9dYCu3ZmHjXQ8"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)

        }else if(projectType.equals("onecanal")){
            val strUri =
                "https://goo.gl/maps/GpfUeLghLoazMquA6"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)

        }else if(projectType.equals("onecresecent")){
            val strUri =
                "https://goo.gl/maps/CdLT53vUyujd63wF9"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)

        }else if(projectType.equals("searenity")){
            val strUri =
                "https://goo.gl/maps/HbhsZqQcAibJ1q9MA"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)

        }else if(projectType.equals("amara")){
            val strUri =
                "https://goo.gl/maps/jnLcwxopCDKU8G6Z9"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)

        }else if(projectType.equals("sunrays")){
            val strUri =
                "https://goo.gl/maps/GdJk65fVhWY8kzgGA"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)

        }else if(projectType.equals("serene")){
            val strUri =
                "https://goo.gl/maps/1KMTP42TKEk9eDcn9"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)

        }else if(projectType.equals("azalea")){
            val strUri =
                "https://goo.gl/maps/jMyhvVb1ktqqoBk57"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)
        }
    }
}