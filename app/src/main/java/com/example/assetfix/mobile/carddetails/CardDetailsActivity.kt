package com.example.assetfix.mobile.carddetails
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.assetfix.R
import com.example.assetfix.mobile.assets.AssetDetailsFragment
import com.example.assetfix.mobile.assets.AssetsFragment
import com.example.assetfix.mobile.dashboard.DashboardFragment
import com.example.assetfix.mobile.inspectionsandsupervision.InspectionsAndSupervisionDetailsFragment
import com.example.assetfix.mobile.inspectionsandsupervision.InspectionsAndSupervisionFragment
import com.example.assetfix.mobile.partsandsupplies.PartsAndSuppliesDetailsFragment
import com.example.assetfix.mobile.partsandsupplies.PartsAndSuppliesFragment
import com.example.assetfix.mobile.peopleandteams.PeopleAndTeamsDetailsFragment
import com.example.assetfix.mobile.peopleandteams.PeopleAndTeamsFragment
import com.example.assetfix.mobile.profile.ProfileFragment
import com.example.assetfix.mobile.vendorsandcustomers.VendorsAndCustomersDetailsFragment
import com.example.assetfix.mobile.vendorsandcustomers.VendorsAndCustomersFragment
import com.example.assetfix.mobile.workOrder.WorkOrderDetailsFragment
import com.example.assetfix.mobile.workOrder.WorkOrderFormFragment
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
        val cardDetailsFragmentName = intent.getStringExtra("cardDetailsFragmentName")
        setSupportActionBar(toolbar)


        val selectedFragment = getCardDetailsFragment()

        if (cardDetailsFragmentName != null) {
            changeActivityTitle(cardDetailsFragmentName)
        }
        
        replaceFragment(selectedFragment)

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

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout, fragment)
        fragmentTransaction.commit()

        //Change Toolbar Title

    }

    private fun changeActivityTitle(newTitle: String) {
        supportActionBar?.title = newTitle
    }

    private fun getCardDetailsFragment(): Fragment {

        val cardDetailsFragmentName = intent.getStringExtra("cardDetailsFragmentName")

        if (cardDetailsFragmentName != null) {
            Log.d("CardDetailsName", cardDetailsFragmentName)
        }

        return when (cardDetailsFragmentName) {
            "Work Order" -> {
                // Your code for Work Order Details Fragment
                WorkOrderDetailsFragment()
            }
            "Assets" -> {
                // Your code for Asset Details Fragment
                AssetDetailsFragment()
            }
            "People & Teams" -> {
                // Your code for People and Teams Details Fragment
                PeopleAndTeamsDetailsFragment()
            }
            "Parts & Supplies" -> {
                // Your code for Parts and Supplies Details Fragment
                PartsAndSuppliesDetailsFragment()
            }
            "Inspections & Supervision" -> {
                // Your code for Inspections and Supervision Details Fragment
                InspectionsAndSupervisionDetailsFragment()
            }
            "Vendors & Customers" -> {
                // Your code for Vendors and Customers Details Fragment
                VendorsAndCustomersDetailsFragment()
            }
            "Create Work Order" -> {
                // Your code for Vendors and Customers Details Fragment
                WorkOrderFormFragment()
            }
            else -> {
                // Your code for the default case
                WorkOrderDetailsFragment() // Handle the default case
            }
        }




    }

}