package com.example.assetfix.mobile.auth

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Patterns
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assetfix.R
import com.example.assetfix.mobile.dashboard.Dashboard
import com.example.assetfix.mobile.welcomepages.FeaturePage
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@Suppress("DEPRECATION")
class LogInpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.assetfix.R.layout.activity_log_inpage)
        val createAccount = findViewById<TextView>(com.example.assetfix.R.id.createAccount)
        createAccount.apply {
            paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
        createAccount.setOnClickListener {
            openSignUpPage()
        }
        val navigateToPreviousPage: ImageButton = findViewById(R.id.navigateBack)
        navigateToPreviousPage.setOnClickListener {
            previousPage()
        }
        val logInButton: MaterialButton = findViewById(R.id.logInButton)
        logInButton.setOnClickListener {
            validateLogIn()
        }

    }

    private fun openSignUpPage() {
        val intent = Intent(this, SignUpPage::class.java)
        startActivity(intent)
        supportFinishAfterTransition()
        overridePendingTransition(R.anim.slide_to_the_right, R.anim.slide_to_the_left)
    }

    private fun previousPage() {
        val intent = Intent(this, FeaturePage::class.java)
        startActivity(intent)
        supportFinishAfterTransition()
        overridePendingTransition(R.anim.slide_from_the_left, R.anim.slide_out_of_the_page_right)
    }
    private fun validateLogIn() {
        val myEmail: TextInputEditText = findViewById(R.id.email_edit_text)
        val email = myEmail.text.toString()
        val myPassword: TextInputEditText = findViewById(R.id.password_edit_text)
        val password = myPassword.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // If email is invalid, show error message and set outline color to red
            myEmail.error = "Please enter a valid email address"
            val emailLayout: TextInputLayout = findViewById(R.id.email)
            emailLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            emailLayout.setBoxStrokeColor(Color.RED)
        } else if (email.isEmpty() || password.isEmpty()) {
            // If email and/or password fields are empty, show error message and set outline color to red
            val emailLayout: TextInputLayout = findViewById(R.id.email)
            emailLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            emailLayout.setBoxStrokeColor(Color.RED)
            val passwordLayout: TextInputLayout = findViewById(R.id.password)
            passwordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            passwordLayout.setBoxStrokeColor(Color.RED)
            passwordLayout.setError("Please enter your password")

        } else if (password.length < 6) {
            // If password is less than 6 characters, show error message and set outline color to red
            val passwordLayout: TextInputLayout = findViewById(R.id.password)
            passwordLayout.setError("Password must be at least 6 characters")
            passwordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            passwordLayout.setBoxStrokeColor(Color.RED)
        } else {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_to_the_right, R.anim.slide_to_the_left)
        }
    }
}