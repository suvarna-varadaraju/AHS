package com.ahs.property.property.resident

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.ahs.property.R
import com.ahs.property.databinding.FragmentResidentBinding
import com.ahs.property.home.ContactFragment
import com.ahs.property.property.Brochers
import com.ahs.property.property.Model.ResidentModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.util.MimeTypes

class ResidentFragment : Fragment(R.layout.fragment_resident),ResidentAdapter.OnItemClickListener,
    ResidentAdapter_onecanal.OnItemClickListener,ResidentAdapter_cresent.OnItemClickListener {
    private lateinit var binding: FragmentResidentBinding
    private var player: ExoPlayer? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResidentBinding.bind(view)
        //initializePlayer("android.resource://${requireActivity().packageName}/${R.raw.casacanal_5sec}")
        setupRecyclerView()
        binding.apply {

           // binding.toolbar.incToolbarImage.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
            toolbar.incToolbarImage.setOnClickListener(View.OnClickListener { view ->
                //onBackPressed()
            })

            btnLogout.setOnClickListener(View.OnClickListener { view ->
               loadFragment(ContactFragment())
            })

            /*val uri = Uri.parse("android.resource://${requireActivity().packageName}/${R.raw.casacanal_5sec}")
            bannerimage.setVideoURI(uri)
           //val mediaController = MediaController(requireActivity())
            //mediaController.setVisibility(View.GONE)
            //mediaController.setAnchorView(bannerimage)
            //mediaController.setMediaPlayer(bannerimage)
            bannerimage.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.isLooping = true
            }
            //bannerimage.setMediaController(mediaController)
            bannerimage.start()*/

            binding.learnmore.setOnClickListener(View.OnClickListener { view ->
                val intent = Intent(requireActivity(), Brochers::class.java)
                intent.putExtra("type","casacanal")
                requireActivity().startActivity(intent)
            })

            binding.learnmore1.setOnClickListener(View.OnClickListener { view ->
                val intent = Intent(requireActivity(), Brochers::class.java)
                intent.putExtra("type","onecanal")
                requireActivity().startActivity(intent)
            })

            binding.learnmore2.setOnClickListener(View.OnClickListener { view ->
                val intent = Intent(requireActivity(), Brochers::class.java)
                intent.putExtra("type","onecresecent")
                requireActivity().startActivity(intent)
            })
        }
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

  /*  fun onBackPressed() {
        val count: Int = requireActivity().getSupportFragmentManager().getBackStackEntryCount()
        if (count == 0) {
            loadFragment(HomeFragment())
        } else {
            requireActivity().getSupportFragmentManager().popBackStack()
        }
    }*/

    private fun setupRecyclerView() {

        binding.recyclerview1.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val snapHelper: SnapHelper = PagerSnapHelper()
            adapter = ResidentAdapter(createHeroList(), requireActivity(), this@ResidentFragment)
            snapHelper.attachToRecyclerView(binding.recyclerview1)
            addItemDecoration(
                CirclePagerIndicatorDecoration(
                    colorInactive = ContextCompat.getColor(context, R.color.black_alpha),
                    colorActive = ContextCompat.getColor(context, R.color.purple_500)
                )
            )
        }

        binding.recyclerview2.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val snapHelper: SnapHelper = PagerSnapHelper()
            adapter = ResidentAdapter_onecanal(
                createCanalList(),
                requireActivity(),
                this@ResidentFragment
            )
            addItemDecoration(
                CirclePagerIndicatorDecoration(
                    colorInactive = ContextCompat.getColor(context, R.color.black_alpha),
                    colorActive = ContextCompat.getColor(context, R.color.purple_500)
                )
            )
            snapHelper.attachToRecyclerView(binding.recyclerview2)
        }

        binding.recyclerview3.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val snapHelper: SnapHelper = PagerSnapHelper()
            adapter = ResidentAdapter_cresent(
                createCresecentList(),
                requireActivity(),
                this@ResidentFragment
            )
            addItemDecoration(
                CirclePagerIndicatorDecoration (
                    colorInactive = ContextCompat.getColor(context, R.color.black_alpha),
                    colorActive = ContextCompat.getColor(context, R.color.purple_500)
                )
            )
            snapHelper.attachToRecyclerView(binding.recyclerview3)
        }
    }

       /* binding.recyclerview1.addOnItemTouchListener(
            RecyclerItemClickListener(
                ctx,
                binding.recyclerview1,
                object : OnItemClickListener() {
                    fun onItemClick(view: View?, position: Int) {
                        val avg: Int =
                            (layoutManager.findFirstCompletelyVisibleItemPosition() + (layoutManager.findFirstCompletelyVisibleItemPosition() + 1) +
                                    layoutManager.findLastCompletelyVisibleItemPosition()) / 3
                        if (position > avg) horzRview.getLayoutManager().smoothScrollToPosition(
                            binding.recyclerview1,
                            null,
                            position + 1
                        ) else if (position != 0) binding.recyclerview1.getLayoutManager()
                            ?.smoothScrollToPosition(
                                binding.recyclerview1,
                                null,
                                position - 1
                            ) else binding.recyclerview1.getLayoutManager()
                                ?.smoothScrollToPosition(binding.recyclerview1, null, 0)
                    }

                    fun onLongItemClick(view: View?, position: Int) {}
                })
        )*/


    private fun createHeroList(): ArrayList<ResidentModel> {
        return arrayListOf<ResidentModel>(
            ResidentModel(
                R.drawable.casacanal_12
            ),
            ResidentModel(
                R.drawable.casacanal_10
            ),
            ResidentModel(
                R.drawable.casacanal_11
            ),
            ResidentModel(
                R.drawable.casacanal_13
            )
        )
    }

    private fun createCanalList(): ArrayList<ResidentModel> {
        return arrayListOf<ResidentModel>(
            ResidentModel(
                R.drawable.onecanal_1
            ),
            ResidentModel(
                R.drawable.onecanal_2
            ),
            ResidentModel(
                R.drawable.onecanal_3
            ),
            ResidentModel(
                R.drawable.onecanal_7
            )
        )
    }

    private fun createCresecentList(): ArrayList<ResidentModel> {
        return arrayListOf<ResidentModel>(
            ResidentModel(
                R.drawable.onecrescent_new
            ),
            ResidentModel(
                R.drawable.onecresecent_2
            ),
            ResidentModel(
                R.drawable.onecresecent_3
            ),
            ResidentModel(
                R.drawable.onecresecent_4
            )
        )
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onItemClick(position: Int, whichview : Int) {
        if(whichview == 1){
            binding.recyclerview1.scrollToPosition(position + 1)
        }else if(whichview == 2){
            binding.recyclerview2.scrollToPosition(position + 1)
        }else if(whichview == 3){
            binding.recyclerview3.scrollToPosition(position + 1)
        }
    }

    override fun onResume() {
        super.onResume()
        player?.stop()
        player?.release()
        initializePlayer("android.resource://${requireActivity().packageName}/${R.raw.casacanal}")
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
}