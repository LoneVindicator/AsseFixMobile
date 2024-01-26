package com.example.assetfix.mobile.carddetails
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
import com.example.assetfix.mobile.vendorsandcustomers.VendorsAndCustomersFragment
import com.example.assetfix.mobile.workOrder.WorkOrderFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class CardDetailsActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        var toolbarTitle = ""
        setSupportActionBar(toolbar)

        replaceFragment(DashboardFragment(), toolbarTitle)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_Layout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(com.google.android.material.R.drawable.ic_arrow_back_black_24)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Handle the home button click, e.g., navigate up or finish the activity
                onBackPressed() // Or any other action you want to perform
                return true
            }
            // Add other menu item handling if needed
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