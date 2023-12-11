package com.ahs.property.about

import android.graphics.PixelFormat
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ahs.property.R
import com.ahs.property.databinding.FragmentAboutBinding
import com.ahs.property.property.villas.villaFragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.util.MimeTypes

class AboutFragment : Fragment(R.layout.fragment_about) {
    private lateinit var binding: FragmentAboutBinding
    private var player: ExoPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)
        binding.toolbar.aboutus.setText("About us")
        requireActivity().getWindow().setFormat(PixelFormat.TRANSLUCENT)
        //binding.toolbar.incToolbarImage.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        binding.toolbar.incToolbarImage.setOnClickListener(View.OnClickListener { view ->
            onBackPressed()
        })

        binding.progressIndicator.cimageOne.setBackgroundResource(R.drawable.ic_icon_background_blue1)
        //insideTheard()

        binding.progressIndicator.cimageOne.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimageOne.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.progressIndicator.year1.setTypeface(binding.progressIndicator.year1.getTypeface(), Typeface.BOLD)
            binding.aboutHistroy.setImageResource(R.drawable.history_2017)
            binding.aboutusHistroyText.setText("AHS Group was established in 2017 with a clear vision for making a positive impact on residential communities.\n\n" +
                    "The founder, Mr. Abbas Sajwani, lead the company with a passion for enriching people's lives.\n\n" +
                    "AHS Group sought to inspire and empower individuals through its innovative solutions and services.")
        })

        binding.progressIndicator.cimageTwo.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimageTwo.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.history_2018)
            binding.progressIndicator.year2.setTypeface(binding.progressIndicator.year2.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS Ventures was established in 2018 with a mission to transform the industry through innovation and excellence. \n\n" +
                    "The company quickly achieved success in this area and expanded its focus to explore new opportunities for growth and impact.\n\n" +
                    "AHS Ventures was and is driven by a relentless pursuit of excellence and a passion for creating positive change in the communities it serves.\n")
        })

        binding.progressIndicator.cimageThree.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimageThree.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.history_2019)
            binding.progressIndicator.year3.setTypeface(binding.progressIndicator.year3.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS embarked on a journey of global expansion, marking a major milestone in its history.\n\n" +
                    "The company's unwavering commitment to excellence and customer satisfaction enabled it to successfully establish branches and corporate offices across the entire GCC region.\n\n" +
                    "AHS continues to push the boundaries of what is possible, inspiring its employees and customers to reach new heights of success and growth.\n")
        })

        binding.progressIndicator.cimageFour.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimageFour.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.history_2020)
            binding.progressIndicator.year4.setTypeface(binding.progressIndicator.year4.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS Investments was established in 2020 with a clear vision for creating long-term value with a focus on a diversified portfolio of private and public equity investments.\n\n" +
                    "AHS Investments reached an impressive asset base of over \$150 million.\n\n" +
                    "The company is dedicated to identifying and supporting innovative companies and corporate entities.\n\n" +
                    "AHS Investments goal is to transform industries and create meaningful change for society.\n")
        })

        binding.progressIndicator.cimage5.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimage5.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.history_2021)
            binding.progressIndicator.year5.setTypeface(binding.progressIndicator.year5.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS Properties was founded in 2021 by Mr. Abbas Sajwani.\n\n" +
                    "Focusing on exceptional real estate developments. First projects include 3 Villas in Palm Jumeirah and 1 Villa in Emirates Hills.\n\n" +
                    "The company quickly emerged as a leader in the industry, setting high bar for quality, innovation, and design.\n\n" +
                    "AHS Properties achieved numerous milestones and accolades in first year of operations.\n")
        })

        binding.progressIndicator.cimage6.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimage6.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.historu_2022)
            binding.progressIndicator.year6.setTypeface(binding.progressIndicator.year6.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS Properties sold three Palm villas for \$75 million.\n\n" +
                    "AHS launched a new ultra-luxury villa in Palm Jumeirah for \$45 million.\n\n" +
                    "AHS announced two new projects in Dubai Water Canal and Palm Jumeirah. \n\n"+
                    "The company's Gross Development Value (GDV) has reached over \$1.5 billion.\n")
        })

        binding.progressIndicator.cimage7.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimage7.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.casacanal_10)
            binding.progressIndicator.year7.setTypeface(binding.progressIndicator.year7.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS Properties launched Casa Canal in 2023, a groundbreaking architectural project.\n\n" +
                    "Exclusive interiors by Fendi Casa.\n" +
                    "AHS Properties continues to meticulously craft residences with spacious layouts and state-of-the-art amenities with breathtaking views to enhance the living experience for residents.\n\n" +
                    "AHS Properties sells Dubai ultra-luxury penthouse for \$50m. \n\n" +
                    "The company's Gross Development Value (GDV) has reached over \$1.65 billion.\n\n")
        })

        binding.progressIndicator.year1.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimageOne.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.progressIndicator.year1.setTypeface(binding.progressIndicator.year1.getTypeface(), Typeface.BOLD)
            binding.aboutHistroy.setImageResource(R.drawable.history_2017)
            binding.aboutusHistroyText.setText("AHS Group was established in 2017 with a clear vision for making a positive impact on residential communities.\n\n" +
                    "The founder, Mr. Abbas Sajwani, lead the company with a passion for enriching people's lives.\n\n" +
                    "AHS Group sought to inspire and empower individuals through its innovative solutions and services.")
        })

        binding.progressIndicator.year2.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimageTwo.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.history_2018)
            binding.progressIndicator.year2.setTypeface(binding.progressIndicator.year2.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS Ventures was established in 2018 with a mission to transform the industry through innovation and excellence. \n\n" +
                    "The company quickly achieved success in this area and expanded its focus to explore new opportunities for growth and impact.\n\n" +
                    "AHS Ventures was and is driven by a relentless pursuit of excellence and a passion for creating positive change in the communities it serves.\n")
        })

        binding.progressIndicator.year3.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimageThree.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.history_2019)
            binding.progressIndicator.year3.setTypeface(binding.progressIndicator.year3.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS embarked on a journey of global expansion, marking a major milestone in its history.\n\n" +
                    "The company's unwavering commitment to excellence and customer satisfaction enabled it to successfully establish branches and corporate offices across the entire GCC region.\n\n" +
                    "AHS continues to push the boundaries of what is possible, inspiring its employees and customers to reach new heights of success and growth.\n")
        })

        binding.progressIndicator.year4.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimageFour.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.history_2020)
            binding.progressIndicator.year4.setTypeface(binding.progressIndicator.year4.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS Investments was established in 2020 with a clear vision for creating long-term value with a focus on a diversified portfolio of private and public equity investments.\n\n" +
                    "AHS Investments reached an impressive asset base of over \$150 million.\n\n" +
                    "The company is dedicated to identifying and supporting innovative companies and corporate entities.\n\n" +
                    "AHS Investments goal is to transform industries and create meaningful change for society.\n")
        })

        binding.progressIndicator.year5.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimage5.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.history_2021)
            binding.progressIndicator.year5.setTypeface(binding.progressIndicator.year5.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS Properties was founded in 2021 by Mr. Abbas Sajwani.\n\n" +
                    "Focusing on exceptional real estate developments. First projects include 3 Villas in Palm Jumeirah and 1 Villa in Emirates Hills.\n\n" +
                    "The company quickly emerged as a leader in the industry, setting high bar for quality, innovation, and design.\n\n" +
                    "AHS Properties achieved numerous milestones and accolades in first year of operations.\n")
        })

        binding.progressIndicator.year6.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimage6.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.historu_2022)
            binding.progressIndicator.year6.setTypeface(binding.progressIndicator.year6.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS Properties sold three Palm villas for \$75 million.\n\n" +
                    "AHS launched a new ultra-luxury villa in Palm Jumeirah for \$45 million.\n\n" +
                    "AHS announced two new projects in Dubai Water Canal and Palm Jumeirah. \n\n"+
                    "The company's Gross Development Value (GDV) has reached over \$1.5 billion.\n")
        })

        binding.progressIndicator.year7.setOnClickListener(View.OnClickListener { view ->
            binding.progressIndicator.cimage7.setBackgroundResource(R.drawable.ic_icon_background_blue1)
            binding.aboutHistroy.setImageResource(R.drawable.casacanal_12)
            binding.progressIndicator.year7.setTypeface(binding.progressIndicator.year7.getTypeface(), Typeface.BOLD)
            binding.aboutusHistroyText.setText("AHS Properties launched Casa Canal in 2023, a groundbreaking architectural project.\n\n" +
                    "Exclusive interiors by Fendi Casa.\n" +
                    "AHS Properties continues to meticulously craft residences with spacious layouts and state-of-the-art amenities with breathtaking views to enhance the living experience for residents.\n\n" +
                    "AHS Properties sells Dubai ultra-luxury penthouse for \$50m. \n\n" +
                    "The company's Gross Development Value (GDV) has reached over \$1.65 billion.\n\n")
                    })
    }

    private fun insideTheard(){
        Thread(Runnable {
            // performing some dummy time taking operation
            /* var i=0
             while(i<Int.MAX_VALUE){
                 i++
             }*/
            Thread.sleep(3000)
            requireActivity().runOnUiThread(java.lang.Runnable {
                binding.progressIndicator.cimageTwo.setBackgroundResource(R.drawable.ic_icon_background_blue1)
                binding.aboutHistroy.setImageResource(R.drawable.history_2018)
                binding.progressIndicator.year2.setTypeface(binding.progressIndicator.year2.getTypeface(), Typeface.BOLD)
                binding.aboutusHistroyText.setText("AHS Ventures was established in 2018 with a mission to transform the industry through innovation and excellence. \n\n" +
                        "The company quickly achieved success in this area and expanded its focus to explore new opportunities for growth and impact.\n\n" +
                        "AHS Ventures was and is driven by a relentless pursuit of excellence and a passion for creating positive change in the communities it serves.\n")
            })

        }).start()

        Thread(Runnable {
            Thread.sleep(6000)
            requireActivity().runOnUiThread(java.lang.Runnable {
                binding.progressIndicator.cimageThree.setBackgroundResource(R.drawable.ic_icon_background_blue1)
                binding.aboutHistroy.setImageResource(R.drawable.history_2019)
                binding.progressIndicator.year3.setTypeface(binding.progressIndicator.year3.getTypeface(), Typeface.BOLD)
                binding.aboutusHistroyText.setText("AHS embarked on a journey of global expansion, marking a major milestone in its history.\n\n" +
                        "The company's unwavering commitment to excellence and customer satisfaction enabled it to successfully establish branches and corporate offices across the entire GCC region.\n\n" +
                        "AHS continues to push the boundaries of what is possible, inspiring its employees and customers to reach new heights of success and growth.\n")
            })
        }).start()

        Thread(Runnable {
            Thread.sleep(9000)
            requireActivity().runOnUiThread(java.lang.Runnable {
                binding.progressIndicator.cimageFour.setBackgroundResource(R.drawable.ic_icon_background_blue1)
                binding.aboutHistroy.setImageResource(R.drawable.history_2020)
                binding.progressIndicator.year4.setTypeface(binding.progressIndicator.year4.getTypeface(), Typeface.BOLD)
                binding.aboutusHistroyText.setText("AHS Investments was established in 2020 with a clear vision for creating long-term value through a diversified portfolio of private and public equity investments.\n\n" +
                        "The company's unwavering commitment to sound investment principles and rigorous due diligence has enabled it to build an impressive asset base of over \$150 million.\n\n" +
                        "AHS Investments is dedicated to identifying and supporting innovative companies and corporate entities that have the potential to transform industries and create meaningful change for society.\n")
            })
        }).start()

        Thread(Runnable {
            Thread.sleep(12000)
            requireActivity().runOnUiThread(java.lang.Runnable {
                binding.progressIndicator.cimage5.setBackgroundResource(R.drawable.ic_icon_background_blue1)
                binding.aboutHistroy.setImageResource(R.drawable.history_2021)
                binding.progressIndicator.year5.setTypeface(binding.progressIndicator.year5.getTypeface(), Typeface.BOLD)
                binding.aboutusHistroyText.setText("AHS Properties was founded with a bold ambition to create exceptional real estate developments that exceed expectations and enhance the lives of residents and communities.\n\n" +
                        "The company's first projects, including 3 villas in Palm Jumeirah and 1 in Emirates Hills, set a high bar for quality, innovation, and design.\n\n" +
                        "AHS Properties has quickly emerged as a leader in the industry, achieving numerous milestones and accolades in its first year of operation and inspiring others to pursue excellence and make a positive impact on the world.\n")
            })
        }).start()

        Thread(Runnable {
            Thread.sleep(15000)
            requireActivity().runOnUiThread(java.lang.Runnable {
                binding.progressIndicator.cimage6.setBackgroundResource(R.drawable.ic_icon_background_blue1)
                binding.aboutHistroy.setImageResource(R.drawable.historu_2022)
                binding.progressIndicator.year6.setTypeface(binding.progressIndicator.year6.getTypeface(), Typeface.BOLD)
                binding.aboutusHistroyText.setText("AHS Properties sold three Palm villas for \$475 million.\n\n" +
                        "AHS launched a new ultra-luxury villa in Palm Jumeirah for \$45 million.\n\n" +
                        "AHS announced two new projects in Dubai Water Canal and Palm Jumeirah, bringing the company's total gross development value to over \$550 million.")
            })
        }).start()

        Thread(Runnable {
            // performing some dummy time taking operation
            Thread.sleep(18000)
            requireActivity().runOnUiThread(java.lang.Runnable {
                binding.progressIndicator.cimage7.setBackgroundResource(R.drawable.ic_icon_background_blue1)
                binding.aboutHistroy.setImageResource(R.drawable.casacanal_10)
                binding.progressIndicator.year7.setTypeface(binding.progressIndicator.year7.getTypeface(), Typeface.BOLD)
                binding.aboutusHistroyText.setText("In 2023, AHS Properties launched an extraordinary project called Casa Canal, marking a significant milestone in architectural innovation. This groundbreaking development combines cutting-edge design, sustainable technology, and unparalleled luxury. Situated alongside a serene canal, Casa Canal offers residents a harmonious blend of natural beauty and urban sophistication. The meticulously crafted residences boast spacious layouts, state-of-the-art amenities, and breathtaking views, providing an unmatched living experience.")
            })
        }).start()
    }

    /*private fun startVideo() {
        val mediaController = MediaController(context)
        mediaController.setVisibility(View.INVISIBLE)
        mediaController.setAnchorView(binding.bannerimage)
        val videoUri = Uri.parse("android.resource://${requireActivity().packageName}/${R.raw.casacanal_5sec}")
        binding.bannerimage.setBackgroundDrawable(null)
        binding.bannerimage.setVideoURI(videoUri)
        binding.bannerimage.setMediaController(mediaController)
        binding.bannerimage.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            mediaPlayer.start()
        }

       *//* binding.bannerimage.postDelayed(Runnable {
            binding.bannerimage.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.isLooping = true
                mediaPlayer.start()
            }
        }, 7500)*//*
    }*/

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    fun onBackPressed() {
        val count: Int = requireActivity().getSupportFragmentManager().getBackStackEntryCount()
        if (count == 0) {
            loadFragment(villaFragment())
        } else {
            requireActivity().getSupportFragmentManager().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        player?.stop()
        player?.release()
        initializePlayer("android.resource://${requireActivity().packageName}/${R.raw.histroy}")
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