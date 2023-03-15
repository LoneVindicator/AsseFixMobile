package com.example.assetfix.mobile.welcomepages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.assetfix.R

class FirstInstallation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_installation_page)
        val getStarted: Button = findViewById(R.id.getStarted)
        getStarted.setOnClickListener{
            openFeaturePage()
        }
    }
    private fun openFeaturePage() {
        val intent = Intent(this, FeaturePage::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_to_the_right, R.anim.slide_to_the_left)
    }

}