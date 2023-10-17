package com.ahs.property.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.ahs.property.R
import com.ahs.property.about.AboutFragment
import com.ahs.property.databinding.ActivityMainBinding
import com.ahs.property.property.resident.ResidentFragment
import com.ahs.property.property.villas.villaFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var menuHome: View
    private lateinit var menuResident: View
    private lateinit var menuVilla: View
    private lateinit var menuContact: View
    private lateinit var menuAbout: View
    private lateinit var viewPager: ViewPager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        menuHome = findViewById<View>(R.id.menu_home)
        menuResident = findViewById<View>(R.id.menu_resident)
        menuVilla = findViewById<View>(R.id.menu_villas)
        menuContact = findViewById<View>(R.id.menu_contact)
        menuAbout = findViewById<View>(R.id.menu_about)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            handleBottomNavigation(
                it.itemId
            )
        }

        binding.bottomNavigationView.selectedItemId = R.id.menu_home

       /* val menuItemView = findViewById<View>(R.id.menu_home)
        val rotate = ObjectAnimator.ofFloat(menuItemView, "rotation", 360f, 0f)
        rotate.setRepeatCount(2)
        rotate.duration = 500
        rotate.start()*/

        //Toast.makeText(this, your_msg, Toast.LENGTH_SHORT).show()

        /*val expandAndShrink = AnimationSet(true)
        val expand = ScaleAnimation(
            1f, 1.2f,
            1f, 1.2f,
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 0f
        )
        expand.duration = 1000

        val shrink = ScaleAnimation(
            1.2f, 1f,
            1.2f, 1f,
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 0f
        )
        shrink.startOffset = 1000
        shrink.duration = 1000

        expandAndShrink.addAnimation(expand)
        expandAndShrink.addAnimation(shrink)
        expandAndShrink.fillAfter = true
        expandAndShrink.interpolator = AccelerateInterpolator(1.0f)

        menuHome.startAnimation(expandAndShrink)*/
        val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        menuHome.startAnimation(shake)
       /* bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
                    menuHome.startAnimation(shake)
                    viewPager.setCurrentItem(0)
                    //loadFragment(HomeFragment())
                    true
                }

                R.id.menu_resident -> {
                    val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
                    menuResident.startAnimation(shake)
                    viewPager.setCurrentItem(1)
                    //loadFragment(ResidentFragment())
                    true
                }

                R.id.menu_villas -> {
                    val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
                    menuVilla.startAnimation(shake)
                    viewPager.setCurrentItem(2)
                   // loadFragment(villaFragment())
                    true
                }

                R.id.menu_about -> {
                    val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
                    menuAbout.startAnimation(shake)
                    viewPager.setCurrentItem(3)
                    //loadFragment(AboutFragment())
                    true
                }

                R.id.menu_contact -> {
                    val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
                    menuContact.startAnimation(shake)
                    viewPager.setCurrentItem(4)
                    //loadFragment(ContactFragment())
                    true
                }
                else -> throw AssertionError()
            }
        }*/
    }

    private fun handleBottomNavigation(
        menuItemId: Int
    ): Boolean = when(menuItemId) {
        R.id.menu_home -> {
            val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            menuHome.startAnimation(shake)
            loadFragment(HomeFragment())
            true
        }
        R.id.menu_resident -> {
            val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            menuResident.startAnimation(shake)
            loadFragment(ResidentFragment())
            true
        }
        R.id.menu_villas -> {
            val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            menuVilla.startAnimation(shake)
            loadFragment(villaFragment())
            true
        }
        R.id.menu_about -> {
            val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            menuAbout.startAnimation(shake)
            loadFragment(AboutFragment())
            true
        }
        R.id.menu_contact -> {
            val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            menuContact.startAnimation(shake)
            loadFragment(ContactFragment())
            true
        }
        else -> false
    }

    private fun loadFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
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

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
            handleBottomNavigation(
                R.menu.bottom_nav_menu
            )
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}