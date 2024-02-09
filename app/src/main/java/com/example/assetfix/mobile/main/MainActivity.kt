package com.example.assetfix.mobile.main
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.assetfix.R
import com.example.assetfix.mobile.assets.AssetsFragment
import com.example.assetfix.mobile.dashboard.DashboardFragment
import com.example.assetfix.mobile.inspectionsandsupervision.InspectionsAndSupervisionFragment
import com.example.assetfix.mobile.partsandsupplies.PartsAndSuppliesFragment
import com.example.assetfix.mobile.peopleandteams.PeopleAndTeamsFragment
import com.example.assetfix.mobile.profile.ProfileFragment
import com.example.assetfix.mobile.qrscanner.QrScannerFragment
import com.example.assetfix.mobile.vendorsandcustomers.VendorsAndCustomersFragment
import com.example.assetfix.mobile.workOrder.WorkOrderFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        var toolbarTitle = ""
        setSupportActionBar(toolbar)

        replaceFragment(DashboardFragment(), toolbarTitle)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_Layout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.background=null


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView: NavigationView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.side_menu_home -> {
                    toolbarTitle = "Home"
                    replaceFragment(DashboardFragment(), toolbarTitle)
                }
                R.id.side_menu_profile -> {
                    toolbarTitle = "Profile"
                    replaceFragment(ProfileFragment(), toolbarTitle)
                }
                R.id.side_menu_assets -> {
                    toolbarTitle = "Assets"
                    replaceFragment(AssetsFragment(), toolbarTitle)
                }
                R.id.side_menu_parts_and_supplies -> {
                    toolbarTitle = "Parts & Supplies"
                    replaceFragment(PartsAndSuppliesFragment(), toolbarTitle)
                }
                R.id.side_menu_people_and_teams -> {
                    toolbarTitle = "People & Teams"
                    replaceFragment(PeopleAndTeamsFragment(), toolbarTitle)
                }
                R.id.side_menu_inspections_and_supervision ->{
                    toolbarTitle = "Inspections & Supervision"
                    replaceFragment(InspectionsAndSupervisionFragment(), toolbarTitle)
                }
                R.id.side_menu_vendors_and_customers ->{
                    toolbarTitle = "Vendors & Customers"
                    replaceFragment(VendorsAndCustomersFragment(), toolbarTitle)
                }
                R.id.side_menu_sign_out ->{
                    Toast.makeText(applicationContext, "Clicked Sign Out", Toast.LENGTH_SHORT).show()
                    toolbarTitle = "Home"
                    replaceFragment(DashboardFragment(), toolbarTitle)
                }
            }
            // Close the drawer after a menu item is selected
            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_Layout)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_nav_menu_home_item -> {

                    toolbarTitle = ""
                    replaceFragment(DashboardFragment(), toolbarTitle)
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_nav_menu_work_order_item -> {
                    toolbarTitle = "Work Order"
                    replaceFragment(WorkOrderFragment(), toolbarTitle)
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_nav_menu_assets_item -> {

                    toolbarTitle = "Assets"
                    replaceFragment(AssetsFragment(), toolbarTitle)
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_nav_menu_people_and_teams_item -> {
                    toolbarTitle = "People & Teams"
                    replaceFragment(PeopleAndTeamsFragment(), toolbarTitle)
                    return@setOnItemSelectedListener true
                }


                // Add other menu item cases as needed
                else -> return@setOnItemSelectedListener false
            }
        }

        val qrScanBtn: FloatingActionButton = findViewById(R.id.qrScanView)

        // Now you can interact with the FloatingActionButton
        qrScanBtn.setOnClickListener {
            toolbarTitle = "Scan QR Code"
            replaceFragment(QrScannerFragment(), toolbarTitle)
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
    private fun replaceFragment(fragment: Fragment, toolbarTitle: String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout, fragment)
        fragmentTransaction.commit()

        //Change Toolbar Title

        changeActivityTitle(toolbarTitle)
    }

    private fun changeActivityTitle(newTitle: String) {
        supportActionBar?.title = newTitle
    }

}