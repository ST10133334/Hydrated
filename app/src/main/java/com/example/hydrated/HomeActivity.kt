package com.example.hydrated

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.hydrated.fragments.AnalyticsFragment
import com.example.hydrated.fragments.HomeFragment
import com.example.hydrated.fragments.SettingsFragment
import com.example.hydrated.fragments.TipsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavView = findViewById(R.id.bottomNavView)
        bottomNavView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.analytics -> replaceFragment(AnalyticsFragment())
                R.id.tips -> replaceFragment(TipsFragment())
                R.id.settings -> replaceFragment(SettingsFragment())
            }
            true
        }
    }

    fun replaceFragment(fragment: Fragment) {
        var fragManager = supportFragmentManager
        var fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.frameLayout, fragment)
        fragTransaction.commit()
    }
}