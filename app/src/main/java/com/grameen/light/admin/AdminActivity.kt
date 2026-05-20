package com.grameen.light.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.grameen.light.R
import com.grameen.light.admin.screens.DashboardFragment
import com.grameen.light.admin.screens.MapFragment
import com.grameen.light.admin.screens.AlertsFragment
import com.grameen.light.admin.screens.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val bottomNav = findViewById<BottomNavigationView>(R.id.admin_bottom_nav)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> loadFragment(DashboardFragment())
                R.id.nav_map -> loadFragment(MapFragment())
                R.id.nav_alerts -> loadFragment(AlertsFragment())
                R.id.nav_settings -> loadFragment(SettingsFragment())
            }
            true
        }

        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_dashboard
            loadFragment(DashboardFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.admin_fragment_container, fragment)
            .commit()
    }

    companion object {
        fun createIntent(context: android.content.Context): Intent {
            return Intent(context, AdminActivity::class.java)
        }
    }
}
