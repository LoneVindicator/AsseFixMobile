package com.example.assetfix.mobile.dashboard
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.assetfix.R
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_drawer)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_Layout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView: NavigationView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_item -> {
                    Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_LONG).show()
                }
                R.id.messages_item -> {
                    Toast.makeText(applicationContext, "Clicked Mesages", Toast.LENGTH_SHORT).show()
                }
                R.id.notification_item -> {
                    Toast.makeText(applicationContext, "Clicked Notifications", Toast.LENGTH_SHORT).show()
                }
                R.id.profile_item -> {
                    Toast.makeText(applicationContext, "Clicked Profile`", Toast.LENGTH_SHORT).show()
                }
                R.id.work_order_item ->{
                    Toast.makeText(applicationContext, "Clicked Work Order`", Toast.LENGTH_SHORT).show()
                }
                R.id.parts_inventory_item ->{
                    Toast.makeText(applicationContext, "Clicked Parts & Inventory`", Toast.LENGTH_SHORT).show()
                }
                R.id.assets_item ->{
                    Toast.makeText(applicationContext, "Clicked Assets`", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val drawerLayout: DrawerLayout = findViewById(R.id.drawer_Layout)
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}