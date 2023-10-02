package com.ahs.property.home

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ahs.property.R
import com.ahs.property.about.AboutFragment
import com.ahs.property.databinding.FragmentContactBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import java.util.regex.Pattern

class ContactFragment : Fragment(R.layout.fragment_contact), OnMapReadyCallback, GoogleMap.OnMapClickListener,
    GoogleMap.OnMarkerClickListener {
    private lateinit var binding: FragmentContactBinding
    private lateinit var mMap: GoogleMap
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var REQUEST_CODE = 101
    private lateinit var supportMapFragment: SupportMapFragment
    private lateinit var latLng : LatLng
    private lateinit var techOffice : LatLng
    private lateinit var salesOffice : LatLng
    private lateinit var locationArrayList: ArrayList<LatLng>
    private lateinit var locationString: ArrayList<String>
    private var player: ExoPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContactBinding.bind(view)
        binding.toolbar.aboutus.setText("Contact us")
        //binding.toolbar.incToolbarImage.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        supportMapFragment = childFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment
        binding.toolbar.incToolbarImage.visibility = View.VISIBLE
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fetchLocation()
        binding.toolbar.incToolbarImage.setOnClickListener(View.OnClickListener { view ->
            onBackPressed()
        })

        binding.imagCall.setOnClickListener(View.OnClickListener { View ->
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:+971 44581821")
            requireActivity().startActivity(callIntent)
        })

        binding.textSendmsg.setOnClickListener(View.OnClickListener { View ->
           if(binding.name.text.isNullOrEmpty()){
               binding.name.setError("Enter Name")
               binding.name.requestFocus()
           }else if(binding.email.text.isNullOrEmpty()){
                binding.email.setError("Enter Email")
               binding.email.requestFocus()
            }else if(binding.phone.text.isNullOrEmpty()){
                binding.phone.setError("Enter Mobile number")
               binding.phone.requestFocus()
            }else{
                if (isValidEmail(binding.email.text.toString())){
                    if(isValidPhoneNumber(binding.phone.text.toString())){
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("info@ahs-properties.com"))
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Enquiry")
                        intent.putExtra(Intent.EXTRA_TEXT, binding.message.text.toString())
                        intent.type = "message/rfc822"
                        startActivity(Intent.createChooser(intent, "Choose an Email client :"))
                    }else{
                        Toast.makeText(
                            requireActivity(),
                            "Thank you for contacting us, a representative from our team will get back to you shortly!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else{
                    Toast.makeText(
                        requireActivity(),
                        "Enter valid email id",
                        Toast.LENGTH_SHORT
                    ).show()
                }
           }
        })

        binding.imagWhatspp.setOnClickListener(View.OnClickListener { View ->
            val url = "https://wa.me/+971 543090418"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        })

        binding.imagMail.setOnClickListener(View.OnClickListener { View ->

            val intent = Intent(Intent.ACTION_VIEW)
            val data = Uri.parse(
                "mailto:info@ahs-properties.com")
            intent.data = data
            startActivity(intent)
        })

        binding.socialF.setOnClickListener(View.OnClickListener { View ->
            openFacebookPage("people/AHS-Properties/100083320485787/")
        })

        binding.socialInst.setOnClickListener(View.OnClickListener { View ->
            launchInsta()
        })

        binding.socialYoutube.setOnClickListener(View.OnClickListener { View ->

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/@ahsproperties")
            intent.setPackage("com.google.android.youtube")
            startActivity(intent)
        })

        binding.socialIn.setOnClickListener(View.OnClickListener { View ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/ahsproperties/")))
        })
        locationArrayList = ArrayList()
        locationString = ArrayList()
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
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

                supportMapFragment.getMapAsync(this)
            }
        })
    }

    fun launchInsta() {
        val uriForApp: Uri = Uri.parse("http://instagram.com/_u/ahs.properties/")
        val forApp = Intent(Intent.ACTION_VIEW, uriForApp)

        val uriForBrowser: Uri = Uri.parse("http://instagram.com/ahs.properties/")
        val forBrowser = Intent(Intent.ACTION_VIEW, uriForBrowser)

        forApp.component =
            ComponentName(
                "com.instagram.android",
                "com.instagram.android.activity.UrlHandlerActivity"
            )

        try {
            startActivity(forApp)
        } catch (e: ActivityNotFoundException) {
            startActivity(forBrowser)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun onBackPressed() {
        val count: Int = requireActivity().getSupportFragmentManager().getBackStackEntryCount()
        if (count == 0) {
            loadFragment(AboutFragment())
        } else {
            requireActivity().getSupportFragmentManager().popBackStack()
        }
    }

    private fun openFacebookPage(pageId: String) {
       // pageId = "people/AHS-Properties/100083320485787/"
        val pageUrl = "https://www.facebook.com/$pageId"
        try {
            val applicationInfo: ApplicationInfo =
                requireActivity().getPackageManager().getApplicationInfo("com.facebook.katana", 0)
            if (applicationInfo.enabled) {
                val versionCode: Int =
                    requireActivity().getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode
                val url: String
                url = if (versionCode >= 3002850) {
                    "fb://facewebmodal/f?href=$pageUrl"
                } else {
                    "fb://page/$pageId"
                }
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            } else {
                throw Exception("Facebook is disabled")
            }
        } catch (e: Exception) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl)))
        }
    }

    private fun isValidPhoneNumber(phoneNumber: CharSequence): Boolean {
        return if (!TextUtils.isEmpty(phoneNumber)) {
            Patterns.PHONE.matcher(phoneNumber).matches()
        } else false
    }

    companion object {
        const val REG = "^(\\+971[\\-\\s]?)?[0]?(91)?[789]\\d{9}\$"
        var PATTERN: Pattern = Pattern.compile(REG)
        fun CharSequence.isPhoneNumber() : Boolean = PATTERN.matcher(this).find()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        googleMap.setOnMapClickListener(this)
        googleMap.setOnMarkerClickListener(this)
        techOffice = LatLng(25.0953478, 55.1702784)
        salesOffice = LatLng(25.2041666, 55.2628507)
        locationArrayList.add(techOffice)
        locationString.add("CORPORATE OFFICE")
        locationArrayList.add(salesOffice)
        locationString.add("SALES CENTRE")
        //val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)

       /* for (i in locationArrayList.indices) {
            mMap.addMarker(MarkerOptions().position(locationArrayList[i]).title("Marker"))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(TamWorth, 15f))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList[i]))
        }*/

        latLng = LatLng(25.188108, 55.244958)
        for (i in locationArrayList.indices) {
            val markerOptions = MarkerOptions().position(locationArrayList[i]).title(locationString.get(i))
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(techOffice))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(techOffice, 10f))
            googleMap.addMarker(markerOptions)
            val marker: Marker = googleMap.addMarker(markerOptions)!!
        }
        /*  val sydney = LatLng(25.095, 55.172)
          mMap.addMarker(MarkerOptions().position(sydney).title("Marker in CASA CANAL"))
          //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
          val zoomLevel = 20f //This goes up to 21
          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(requireActivity(),
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


    override fun onMarkerClick(marker: Marker): Boolean {
        val markerName = marker.title
        System.out.println("markerName " + markerName)
        if(markerName.equals("CORPORATE OFFICE")){
            val strUri =
                "https://goo.gl/maps/t1Th2vujSqsZKG986"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)

        }else if(markerName.equals("SALES CENTRE")){
            val strUri =
                "https://goo.gl/maps/WtzHb5xxHTkwdTF79"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)
        }
        return true
    }

    override fun onMapClick(p0: LatLng) {
            val strUri =
                "https://goo.gl/maps/t1Th2vujSqsZKG986"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        player?.stop()
        player?.release()
        initializePlayer("android.resource://${requireActivity().packageName}/${R.raw.aboutcompany}")
    }

    override fun onPause() {
        super.onPause()
        player?.stop()
        player?.release()
    }

    override fun onStop() {
        super.onStop()
        player?.stop()
        player?.release()
    }

    private fun initializePlayer(video : String) {
        player = ExoPlayer.Builder(requireActivity()) // <- context
            .build()

        val mediaItem = MediaItem.Builder()
            .setUri(video)
            .setMimeType(MimeTypes.APPLICATION_MP4)
            .build()

        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultDataSource.Factory(requireActivity()) // <- context
        ).createMediaSource(mediaItem)
        player!!.apply {
            setMediaSource(mediaSource)
            playWhenReady = true
            seekTo(0, 100L)
            prepare()
        }.also {
            binding.playerView.player = it
            player!!.setRepeatMode(Player.REPEAT_MODE_ONE)
        }
    }
}