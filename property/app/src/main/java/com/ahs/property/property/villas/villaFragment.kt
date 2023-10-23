package com.ahs.property.property.villas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.MediaController
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.ahs.property.R
import com.ahs.property.databinding.FragmentVillaBinding
import com.ahs.property.home.ContactFragment
import com.ahs.property.property.Brochers
import com.ahs.property.property.Model.ResidentModel
import com.ahs.property.property.resident.CirclePagerIndicatorDecoration
import com.ahs.property.property.resident.ResidentFragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.util.MimeTypes

class villaFragment : Fragment(R.layout.fragment_villa),VillaAdapter.OnItemClickListener,
    VillaAdapter1.OnItemClickListener,VillaAdapter2.OnItemClickListener,VillaAdapter3.OnItemClickListener,
    VillaAdapter4.OnItemClickListener {
    private lateinit var binding: FragmentVillaBinding
    private var player: ExoPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVillaBinding.bind(view)
        binding.toolbar.aboutus.setText("Villas")
        setupRecyclerView()
        binding.apply {
            /*val mediaController = MediaController(context)
            mediaController.setVisibility(View.INVISIBLE)
            val videoUri = Uri.parse("android.resource://${requireActivity().packageName}/${R.raw.serenity_video}")
            bannerimage.setVideoURI(videoUri)
            bannerimage.setMediaController(mediaController)
            bannerimage.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.isLooping = true
                mediaPlayer.start()
            }*/

            btnLogout.setOnClickListener(View.OnClickListener { view ->
                loadFragment(ContactFragment())
            })

            //binding.toolbar.incToolbarImage.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

            learnmore1.setOnClickListener(View.OnClickListener { view ->
                val intent = Intent(requireActivity(), Brochers::class.java)
                intent.putExtra("type","searenity")
                requireActivity().startActivity(intent)
            })

            learnmore.setOnClickListener(View.OnClickListener { view ->
                val intent = Intent(requireActivity(), Brochers::class.java)
                intent.putExtra("type","amara")
                requireActivity().startActivity(intent)
            })

            learnmore2.setOnClickListener(View.OnClickListener { view ->
                val intent = Intent(requireActivity(), Brochers::class.java)
                intent.putExtra("type","sunrays")
                requireActivity().startActivity(intent)
            })

            binding.learnmore11.setOnClickListener(View.OnClickListener { view ->
                val intent = Intent(requireActivity(), Brochers::class.java)
                intent.putExtra("type","serene")
                requireActivity().startActivity(intent)
            })

            binding.learnmore21.setOnClickListener(View.OnClickListener { view ->
                val intent = Intent(requireActivity(), Brochers::class.java)
                intent.putExtra("type","azalea")
                requireActivity().startActivity(intent)
            })
        }
        binding.toolbar.incToolbarImage.setOnClickListener(View.OnClickListener { view ->
            onBackPressed()
        })
    }

    fun onBackPressed() {
        val count: Int = requireActivity().getSupportFragmentManager().getBackStackEntryCount()
        if (count == 0) {
            loadFragment(ResidentFragment())
        } else {
            requireActivity().getSupportFragmentManager().popBackStack()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerview1.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val snapHelper: SnapHelper = PagerSnapHelper()
            adapter = VillaAdapter(createAmaraList(),requireActivity(),this@villaFragment)
            snapHelper.attachToRecyclerView(binding.recyclerview1);
            addItemDecoration(
                CirclePagerIndicatorDecoration(
                    colorInactive = ContextCompat.getColor(context, R.color.black_alpha),
                    colorActive = ContextCompat.getColor(context, R.color.purple_500),
                )
            )
        }

        binding.recyclerview2.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val snapHelper: SnapHelper = PagerSnapHelper()
            adapter = VillaAdapter1(createSerenityList(),requireActivity(),this@villaFragment)
            snapHelper.attachToRecyclerView(binding.recyclerview2)
            addItemDecoration(
                CirclePagerIndicatorDecoration(
                    colorInactive = ContextCompat.getColor(context, R.color.black_alpha),
                    colorActive = ContextCompat.getColor(context, R.color.purple_500)
                )
            )
        }

        binding.recyclerview3.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = VillaAdapter2(createSunRayList(),requireActivity(),this@villaFragment)
            val snapHelper: SnapHelper = PagerSnapHelper()
            addItemDecoration(
                CirclePagerIndicatorDecoration(
                    colorInactive = ContextCompat.getColor(context, R.color.black_alpha),
                    colorActive = ContextCompat.getColor(context, R.color.purple_500)
                )
            )
            snapHelper.attachToRecyclerView(binding.recyclerview3)
        }

        binding.recyclerview31.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = VillaAdapter3(createAZALEAList(),requireActivity(),this@villaFragment)
            val snapHelper: SnapHelper = PagerSnapHelper()
            addItemDecoration(
                CirclePagerIndicatorDecoration(
                    colorInactive = ContextCompat.getColor(context, R.color.black_alpha),
                    colorActive = ContextCompat.getColor(context, R.color.purple_500)
                )
            )
            snapHelper.attachToRecyclerView(binding.recyclerview31)
        }

        binding.recyclerview12.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = VillaAdapter4(createSERENEList(),requireActivity(),this@villaFragment)
            val snapHelper: SnapHelper = PagerSnapHelper()
            addItemDecoration(
                CirclePagerIndicatorDecoration(
                    colorInactive = ContextCompat.getColor(context, R.color.black_alpha),
                    colorActive = ContextCompat.getColor(context, R.color.purple_500)
                )
            )
            snapHelper.attachToRecyclerView(binding.recyclerview12)
        }

       /* binding.scroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            Handler(Looper.getMainLooper()).postDelayed({
                if (v.getChildAt(0).bottom <= binding.scroll.getHeight() + scrollY) {
                    loadFragment(AboutFragment())
                }
            }, 2000)
        })*/

      /*  binding.viewproject11.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://ahs-properties.com/project/amara/"))
            startActivity(browserIntent)
        }

        binding.viewproject21.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://ahs-properties.com/project/one-canal/")
            )
            startActivity(browserIntent)
        }

        binding.viewproject31.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://1onecrescent.com/"))
            startActivity(browserIntent)
        }

        binding.download11.setOnClickListener {
            var download= requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var PdfUri = Uri.parse("https://ahs-properties.com/wp-content/uploads/2023/05/Amara-L22-EMRHLS-AHS-Properties-1_compressed.pdf")
            var getPdf = DownloadManager.Request(PdfUri)
            getPdf.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            download.enqueue(getPdf)
            Toast.makeText(requireActivity(),"Download Started", Toast.LENGTH_LONG).show()
        }

        binding.download21.setOnClickListener {
            var download= requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var PdfUri = Uri.parse("https://ahs-properties.com/wp-content/uploads/2023/05/One-Canal-Brochure_compressed.pdf")
            var getPdf = DownloadManager.Request(PdfUri)
            getPdf.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            download.enqueue(getPdf)
            Toast.makeText(requireActivity(),"Download Started", Toast.LENGTH_LONG).show()
        }

        binding.download31.setOnClickListener {
            var download= requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var PdfUri = Uri.parse("https://ahs-properties.com/wp-content/uploads/2023/05/One-Crescent-Brochure_compressed.pdf")
            var getPdf = DownloadManager.Request(PdfUri)
            getPdf.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            download.enqueue(getPdf)
            Toast.makeText(requireActivity(),"Download Started", Toast.LENGTH_LONG).show()
        }*/
    }

    private fun createAmaraList(): ArrayList<ResidentModel> {
        return arrayListOf<ResidentModel>(
            ResidentModel(
                R.drawable.villa_amara2
            ),
            ResidentModel(
                R.drawable.villa_amara1
            )
        )
    }

    private fun createSerenityList(): ArrayList<ResidentModel> {
        return arrayListOf<ResidentModel>(
            ResidentModel(
                R.drawable.villa_serenity1
            ),
            ResidentModel(
                R.drawable.villa_serenity
            )
        )
    }

    private fun createSunRayList(): ArrayList<ResidentModel> {
        return arrayListOf<ResidentModel>(
            ResidentModel(
                R.drawable.villa_sunrays2
            ),
            ResidentModel(
                R.drawable.villa_sunrays1
            )
        )
    }

    private fun createAZALEAList(): ArrayList<ResidentModel> {
        return arrayListOf<ResidentModel>(
            ResidentModel(
                R.drawable.villa_azalea1
            ),
            ResidentModel(
                R.drawable.villa_azalea2
            ),
        )
    }

    private fun createSERENEList(): ArrayList<ResidentModel> {
        return arrayListOf<ResidentModel>(
            ResidentModel(
                R.drawable.villa_serene2
            ),
            ResidentModel(
                R.drawable.villa_serene1
            )
        )
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onItemClick(position: Int, whichview: Int) {
        if(whichview == 1){
            binding.recyclerview1.scrollToPosition(position + 1)
        }else if(whichview == 2){
            binding.recyclerview2.scrollToPosition(position + 1)
        }else if(whichview == 3){
            binding.recyclerview3.scrollToPosition(position + 1)
        }else if(whichview == 4){
            binding.recyclerview31.scrollToPosition(position + 1)
        }else if(whichview == 5){
            binding.recyclerview12.scrollToPosition(position + 1)
        }
    }

    override fun onResume() {
        super.onResume()
        player?.stop()
        player?.release()
        initializePlayer("android.resource://${requireActivity().packageName}/${R.raw.serenity}")
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