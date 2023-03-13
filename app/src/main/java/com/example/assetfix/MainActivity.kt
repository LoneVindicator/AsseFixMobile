package com.example.assetfix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val getStarted: Button = findViewById(R.id.getStarted)
        getStarted.setOnClickListener{
            openFeaturePage()
        }
    }
    private fun openFeaturePage() {
        val intent = Intent(this,FeaturePage::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_to_the_right,R.anim.slide_to_the_left)
    }
}