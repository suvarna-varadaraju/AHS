package com.ahs.property.property

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ahs.property.MapsActivity
import com.ahs.property.R
import com.ahs.property.databinding.ActivityBrochersBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task

class Brochers : AppCompatActivity() , AdapterView.OnItemClickListener {
    private lateinit var binding: ActivityBrochersBinding
    private var player: ExoPlayer? = null
    private lateinit var projectType: String
    var popupWindowDogs: PopupWindow? = null
    lateinit var casaCanal: ArrayList<String>
    lateinit var oneCanal: ArrayList<String>
    lateinit var oneCresent: ArrayList<String>
    lateinit var serinity: ArrayList<String>
    lateinit var amara: ArrayList<String>
    lateinit var serene: ArrayList<String>
    lateinit var alzea: ArrayList<String>
    lateinit var sunRays: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrochersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestDeviceLocationSettings()
        val intent = getIntent()
        projectType = intent.getStringExtra("type").toString()
        binding.toolbar.incToolbarImage.visibility = View.VISIBLE
        if(projectType.equals("casacanal")){
            initCasaCanalList()
            popupWindowDogs = popupWindowDogs(casaCanal)
            binding.buttonShowDropDown.setImageResource(R.drawable.casacanal_01)
            initializePlayer("android.resource://${this.packageName}/${R.raw.casacanal}")
            binding.bannerimage.visibility = View.GONE
            binding.playerView.visibility = View.VISIBLE

        }else if(projectType.equals("onecanal")){
            initializePlayer("android.resource://${this.packageName}/${R.raw.onecanal_5sec}")
            initOneCanalList()
            popupWindowDogs = popupWindowDogs(oneCanal)
            binding.buttonShowDropDown.setImageResource(R.drawable.onecanal_01)
            binding.bannerimage.visibility = View.GONE
            binding.playerView.visibility = View.VISIBLE

        }else if(projectType.equals("onecresecent")){
            initializePlayer("android.resource://${this.packageName}/${R.raw.onecanal_5sec}")
            initOneCresecentList()
            popupWindowDogs = popupWindowDogs(oneCresent)
            binding.buttonShowDropDown.setImageResource(R.drawable.onecrescent_01)
            binding.bannerimage.visibility = View.GONE
            binding.playerView.visibility = View.VISIBLE

        }else if(projectType.equals("searenity")){
            initializePlayer("android.resource://${this.packageName}/${R.raw.serenity}")
            initSerenityList()
            binding.toolbar.aboutus.setText("Villas")
            popupWindowDogs = popupWindowDogs(serinity)
            binding.buttonShowDropDown.setImageResource(R.drawable.searenity_02)
            binding.bannerimage.visibility = View.GONE
            binding.playerView.visibility = View.VISIBLE

        }else if(projectType.equals("amara")){
            initAmaraList()
            binding.toolbar.aboutus.setText("Villas")
            popupWindowDogs = popupWindowDogs(amara)
            binding.buttonShowDropDown.setImageResource(R.drawable.amara_02)
            binding.bannerimage.visibility = View.VISIBLE
            binding.bannerimage.setImageResource(R.drawable.villa_amara1)
            binding.playerView.visibility = View.GONE

        }else if(projectType.equals("sunrays")){
            initSunraysList()
            binding.toolbar.aboutus.setText("Villas")
            popupWindowDogs = popupWindowDogs(sunRays)
            binding.buttonShowDropDown.setImageResource(R.drawable.sunrays_01)
            binding.bannerimage.visibility = View.VISIBLE
            binding.bannerimage.setImageResource(R.drawable.villa_sunrays1)
            binding.playerView.visibility = View.GONE

        }else if(projectType.equals("serene")){
            initSereneList()
            binding.toolbar.aboutus.setText("Villas")
            popupWindowDogs = popupWindowDogs(serene)
            binding.buttonShowDropDown.setImageResource(R.drawable.serene_01)
            binding.bannerimage.visibility = View.VISIBLE
            binding.bannerimage.setImageResource(R.drawable.villa_serene1)
            binding.playerView.visibility = View.GONE

        }else if(projectType.equals("azalea")){
            initAlzeaList()
            binding.toolbar.aboutus.setText("Villas")
            popupWindowDogs = popupWindowDogs(alzea)
            binding.buttonShowDropDown.setImageResource(R.drawable.azalea_01)
            binding.bannerimage.visibility = View.VISIBLE
            binding.bannerimage.setImageResource(R.drawable.villa_azalea1)
            binding.playerView.visibility = View.GONE
        }

        val handler = View.OnClickListener { v ->
            when (v.id) {
                R.id.textcasa11 ->                     // show the list view as dropdown
                    popupWindowDogs!!.showAsDropDown(v, -5, 0)
            }
        }
        binding.textcasa11.setOnClickListener(handler)
        binding.toolbar.incToolbarImage.setOnClickListener { this@Brochers.onBackPressedDispatcher.onBackPressed() }
    }

    fun popupWindowDogs(projecttype:ArrayList<String>): PopupWindow? {

        val popupWindow = PopupWindow(this)
        val listViewDogs = ListView(this)
        listViewDogs.setAdapter(CustomAdapter(this,projecttype))
        listViewDogs.setOnItemClickListener(this)
        popupWindow.isFocusable = true
        popupWindow.width = WindowManager.LayoutParams.MATCH_PARENT
        popupWindow.height = WindowManager.LayoutParams.WRAP_CONTENT
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.nav_background1))
        popupWindow.contentView = listViewDogs
        return popupWindow
    }

    private fun initCasaCanalList() {
        casaCanal = ArrayList()
        casaCanal.add("Project Brochure")
        casaCanal.add("Fact Sheet")
        casaCanal.add("3 Bedroom Penthouse")
        casaCanal.add("Garden Villa")
        casaCanal.add("4 Bedroom Sky Villa")
        casaCanal.add("5 Bedroom Penthouse")
        casaCanal.add("6 Bedroom Sky Mansion")
        casaCanal.add("Location")
        casaCanal.add("Website")
    }

    private fun initAlzeaList() {
        alzea = ArrayList()
        alzea.add("Project Brochure")
        alzea.add("Location")
        alzea.add("Website")
    }

    private fun initOneCanalList() {
        oneCanal = ArrayList()
        oneCanal.add("Project Brochure")
        oneCanal.add("Fact Sheet")
        oneCanal.add("Location")
        oneCanal.add("Website")
    }

    private fun initOneCresecentList() {
        oneCresent = ArrayList()
        oneCresent.add("Project Brochure")
        oneCresent.add("Fact Sheet")
        oneCresent.add("6 Bedroom sky palace villa")
        oneCresent.add("Location")
        oneCresent.add("Website")
    }

    private fun initSerenityList() {
        serinity = ArrayList()
        serinity.add("Project Brochure")
        serinity.add("Location")
        serinity.add("Website")
    }

    private fun initAmaraList() {
        amara = ArrayList()
        amara.add("Project Brochure")
        amara.add("Location")
        amara.add("Website")
    }

    private fun initSereneList() {
        serene = ArrayList()
        serene.add("Project Brochure")
        serene.add("Location")
        serene.add("Website")
    }

    private fun initSunraysList() {
        sunRays = ArrayList()
        sunRays.add("Project Brochure")
        sunRays.add("Location")
        sunRays.add("Website")
    }

    /* binding.textcresentDownload.setOnClickListener(View.OnClickListener { view ->
         var download = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
         var PdfUri =
             Uri.parse("https://ahs-properties.com/wp-content/uploads/2023/06/casa-canal-brochure.pdf")
         var getPdf = DownloadManager.Request(PdfUri)
         getPdf.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
         download.enqueue(getPdf)
         Toast.makeText(this@Brochers, "Download Started", Toast.LENGTH_LONG).show()
     })*/

    private fun initializePlayer(video : String) {
        player = ExoPlayer.Builder(this@Brochers) // <- context
            .build()

        // create a media item.
        val mediaItem = MediaItem.Builder()
            .setUri(video)
            .setMimeType(MimeTypes.APPLICATION_MP4)
            .build()

        // Create a media source and pass the media item
        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultDataSource.Factory(this@Brochers) // <- context
        )
            .createMediaSource(mediaItem)
        // Finally assign this media source to the player
        player!!.apply {
            setMediaSource(mediaSource)
            playWhenReady = true // start playing when the exoplayer has setup
            seekTo(0, 0L) // Start from the beginning
            prepare() // Change the state from idle.
        }.also {
            // Do not forget to attach the player to the view
            binding.playerView.player = it
            player!!.setRepeatMode(Player.REPEAT_MODE_ONE)
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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val mContext: Context = view!!.getContext()
        val mainActivity = mContext as Brochers

        val fadeInAnimation: Animation =
            AnimationUtils.loadAnimation(view?.getContext(), android.R.anim.fade_in)
        fadeInAnimation.duration = 10
        view?.startAnimation(fadeInAnimation)
        mainActivity.popupWindowDogs?.dismiss()
        System.out.println("get position " + position + " " + id + " " + projectType)

        if(projectType.equals("casacanal")){
            when(position) {
                0 -> {
                    if(isNetworkAvailable(this)) {
                        val intent = Intent(this@Brochers, PDFVIew::class.java)
                        intent.putExtra("url", "casacanal_projectbrochure.pdf")
                        intent.putExtra(
                            "download",
                            "https://drive.google.com/uc?export=download&id=14kIr17-09sh4CTc_Sy5RtJhBufPwpsiz"
                        )
                        intent.putExtra(
                            "view",
                            "https://drive.google.com/uc?export=view&id=14kIr17-09sh4CTc_Sy5RtJhBufPwpsiz"
                        )
                        startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                1 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","casacanal_factsheet.pdf")
                    intent.putExtra("download","https://drive.google.com/uc?export=download&id=1w0PtXQcjN_tyLhN8pVdgy6tv4wI6KV5r")
                    intent.putExtra("view","https://drive.google.com/uc?export=view&id=1w0PtXQcjN_tyLhN8pVdgy6tv4wI6KV5r")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                2 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","casacanal_3bedroom.pdf")
                    intent.putExtra("download","https://drive.google.com/uc?export=download&id=1Xmoy0Km0_J53UUMxyECk6wBDkQRfw2wr")
                    intent.putExtra("view","https://drive.google.com/uc?export=view&id=1Xmoy0Km0_J53UUMxyECk6wBDkQRfw2wr")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                //garden villa
                3 -> {
                    if(isNetworkAvailable(this)) {
                        val intent = Intent(this@Brochers, PDFVIew::class.java)
                        intent.putExtra("url","casacanal_4bedroomskyvilla.pdf")
                        intent.putExtra("download","https://drive.google.com/uc?export=download&id=1bqEhA99EAunKvpKmI6_U0xZBLwstw1g6")
                        intent.putExtra("view","https://drive.google.com/uc?export=view&id=1bqEhA99EAunKvpKmI6_U0xZBLwstw1g6")
                        startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                4 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","casacanal_4bedroomskyvilla.pdf")
                        intent.putExtra("download","https://drive.google.com/uc?export=download&id=1W50GMBFqPyRDx92IZaSSI6ECHUYBDtgB")
                        intent.putExtra("view","https://drive.google.com/uc?export=view&id=1W50GMBFqPyRDx92IZaSSI6ECHUYBDtgB")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                5 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","casacanal_5bedroomskyvilla.pdf")
                    intent.putExtra("download","https://drive.google.com/uc?export=download&id=19ZsZFeZk8x6m_yC43aeNm2tPLORqWw_1")
                    intent.putExtra("view","https://drive.google.com/uc?export=view&id=19ZsZFeZk8x6m_yC43aeNm2tPLORqWw_1")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                6 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","casacanal_6bedroomskymansion.pdf")
                    intent.putExtra("download","https://drive.google.com/uc?export=download&id=1CdWzXQIOV5Ec9MHyV-JJUBRQoyxHYnCi")
                    intent.putExtra("view","https://drive.google.com/uc?export=view&id=1CdWzXQIOV5Ec9MHyV-JJUBRQoyxHYnCi")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                7 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, MapsActivity::class.java)
                    intent.putExtra("type",projectType)
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                8 -> {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://casacanal.com/"))
                    startActivity(browserIntent)
                }
            }

        }else if(projectType.equals("onecanal")){
            when(position) {
                0 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","onecanal_brochure.pdf")
                    intent.putExtra("download","https://drive.google.com/uc?export=download&id=1qLm3-j0SJe0sfYPjefOJWEgnpktOn3th")
                    intent.putExtra("view","https://drive.google.com/uc?export=view&id=1qLm3-j0SJe0sfYPjefOJWEgnpktOn3th")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                1 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","onecanal_factsheet.pdf")
                    intent.putExtra("download","https://drive.google.com/uc?export=download&id=18QFgXyJWrzVuxtfh72JIQjWF9DfKdpKN")
                    intent.putExtra("view","https://drive.google.com/uc?export=view&id=18QFgXyJWrzVuxtfh72JIQjWF9DfKdpKN")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                2 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, MapsActivity::class.java)
                    intent.putExtra("type",projectType)
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                3 -> {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://ahs-properties.com/project/one-canal/")
                    )
                    startActivity(browserIntent)
                }
            }
        }else if(projectType.equals("onecresecent")){
            when(position) {
                0 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","onecrescent_brochure.pdf")
                    intent.putExtra("download","https://drive.google.com/uc?export=download&id=1qnuCYZTnkGLxUjYUeL5hsX7p0zMPVsO8")
                    intent.putExtra("view","https://drive.google.com/uc?export=view&id=1qnuCYZTnkGLxUjYUeL5hsX7p0zMPVsO8")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                1 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","factsheet_onecrescent.pdf")
                    intent.putExtra("download","https://drive.google.com/uc?export=download&id=1VTQ700GavcG5v93LdnY70tVzltCBPEVC")
                    intent.putExtra("view","https://drive.google.com/uc?export=view&id=1VTQ700GavcG5v93LdnY70tVzltCBPEVC")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                2 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","6bedroom_unit_brochure.pdf")
                    intent.putExtra("download","https://drive.google.com/uc?export=download&id=1aP9IT5hThMQiYE5JIbjwL-c44aJUGv_P")
                    intent.putExtra("view","https://drive.google.com/uc?export=view&id=1aP9IT5hThMQiYE5JIbjwL-c44aJUGv_P")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                3 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, MapsActivity::class.java)
                    intent.putExtra("type",projectType)
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                4 -> {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://1onecrescent.com/"))
                    startActivity(browserIntent)
                }
            }
        }else if(projectType.equals("searenity")){
            when(position) {
                0 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","searenity_brochure.pdf")
                    intent.putExtra("name",true)
                    intent.putExtra("download","https://ahs-properties.com/wp-content/uploads/2023/05/K70-SeaRenity-Brochure_compressed.pdf")
                    intent.putExtra("view","https://ahs-properties.com/wp-content/uploads/2023/05/K70-SeaRenity-Brochure_compressed.pdf")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                1 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, MapsActivity::class.java)
                    intent.putExtra("type",projectType)
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                2 -> {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ahs-properties.com/project/searenity/"))
                    startActivity(browserIntent)
                }
            }

        }else if(projectType.equals("amara")){
            when(position) {
                0 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","amara.pdf")
                    intent.putExtra("name",true)
                    intent.putExtra("download","https://ahs-properties.com/wp-content/uploads/2023/05/Amara-L22-EMRHLS-AHS-Properties-1_compressed.pdf")
                    intent.putExtra("view","https://ahs-properties.com/wp-content/uploads/2023/05/Amara-L22-EMRHLS-AHS-Properties-1_compressed.pdf")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                1 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, MapsActivity::class.java)
                    intent.putExtra("type",projectType)
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                2 -> {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ahs-properties.com/project/amara/"))
                    startActivity(browserIntent)
                }
            }
        }else if(projectType.equals("sunrays")){
            when(position) {
                0 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","sunrays_brochure.pdf")
                    intent.putExtra("name",true)
                    intent.putExtra("download","https://ahs-properties.com/wp-content/uploads/2023/05/C128-Sun-Rays-Brochure_compressed.pdf")
                    intent.putExtra("view","https://ahs-properties.com/wp-content/uploads/2023/05/C128-Sun-Rays-Brochure_compressed.pdf")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                1 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, MapsActivity::class.java)
                    intent.putExtra("type",projectType)
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                2 -> {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ahs-properties.com/project/sun-rays/"))
                    startActivity(browserIntent)
                }
            }
        }else if(projectType.equals("serene")){
            when(position) {
                0 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","serene_brochure.pdf")
                    intent.putExtra("name",true)
                    intent.putExtra("download","https://ahs-properties.com/wp-content/uploads/2023/05/K73-Serene-Brochure_compressed.pdf")
                    intent.putExtra("view","https://ahs-properties.com/wp-content/uploads/2023/05/K73-Serene-Brochure_compressed.pdf")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                1 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, MapsActivity::class.java)
                    intent.putExtra("type",projectType)
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                2 -> {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ahs-properties.com/project/serene/"))
                    startActivity(browserIntent)
                }
            }

        }else if(projectType.equals("azalea")){
            when(position) {
                0 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, PDFVIew::class.java)
                    intent.putExtra("url","azalea_brochure.pdf")
                    intent.putExtra("name",true)
                    intent.putExtra("download","https://ahs-properties.com/wp-content/uploads/2023/05/N49-Azalea-Brochure_compressed.pdf")
                    intent.putExtra("view","https://ahs-properties.com/wp-content/uploads/2023/05/N49-Azalea-Brochure_compressed.pdf")
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                1 -> {
                    if(isNetworkAvailable(this)) {
                    val intent = Intent(this@Brochers, MapsActivity::class.java)
                    intent.putExtra("type",projectType)
                    startActivity(intent)
                    }else{
                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                2 -> {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ahs-properties.com/project/azalea/"))
                    startActivity(browserIntent)
                }
            }
        }
        //casacanal(pdfurl)
       // val selectedItemText = (view as TextView).text.toString()

        //Toast.makeText(mContext, " $selectedItemText", Toast.LENGTH_SHORT).show()
    }

    fun requestDeviceLocationSettings() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(this@Brochers)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener { locationSettingsResponse ->
            val state = locationSettingsResponse.locationSettingsStates

            val label =
                "GPS >> (Present: ${state?.isGpsPresent}  | Usable: ${state?.isGpsUsable} ) \n\n" +
                        "Network >> ( Present: ${state?.isNetworkLocationPresent} | Usable: ${state?.isNetworkLocationUsable} ) \n\n" +
                        "Location >> ( Present: ${state?.isLocationPresent} | Usable: ${state?.isLocationUsable} )"

        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(
                        this@Brochers,
                        100
                    )
                } catch (sendEx: IntentSender.SendIntentException) {

                }
            }
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}