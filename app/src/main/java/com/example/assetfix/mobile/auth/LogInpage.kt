package com.example.assetfix.mobile.auth

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assetfix.R
import com.example.assetfix.mobile.main.MainActivity
import com.example.assetfix.mobile.welcomepages.FeaturePage
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


@Suppress("DEPRECATION")
class LogInpage : AppCompatActivity() {

    // <--- API Stuff --->

    // Retrofit instance
    private var gson: Gson? = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://test.assetfix.co/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    // ApiService interface
    private val apiService = retrofit.create(ApiService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_inpage)


        val createAccount = findViewById<TextView>(R.id.createAccount)
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

            //Uncomment when implementing Login

            validateLogIn()

            //Uncomment to disable login

//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            overridePendingTransition(R.anim.slide_to_the_right, R.anim.slide_to_the_left)
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

        val emailLayout: TextInputLayout = findViewById(R.id.email)
        val passwordLayout: TextInputLayout = findViewById(R.id.password)

        if (email.isEmpty()) {
            emailLayout.error = "Please enter your email address"
            emailLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            emailLayout.setBoxStrokeColor(Color.RED)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // If email is invalid, show error message and set outline color to red
            emailLayout.error = "Please enter a valid email address"
            emailLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            emailLayout.setBoxStrokeColor(Color.RED)
        } else if (password.isEmpty()) {
            passwordLayout.error = "Please enter your password"
            passwordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            passwordLayout.setBoxStrokeColor(Color.RED)
        } else if (password.length < 6) {
            // If password is less than 6 characters, show error message and set outline color to red
            passwordLayout.error = "Password must be at least 6 characters"
            passwordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            passwordLayout.setBoxStrokeColor(Color.RED)
        } else {

            authenticateLogin(email, password)
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            overridePendingTransition(R.anim.slide_to_the_right, R.anim.slide_to_the_left)
        }
    }

    private fun authenticateLogin(email: String, password: String) {


        // Create authentication request body
        val requestBody = AuthenticationRequestBody(email, password)

        Log.d("APIBS", requestBody.toString())  // Debug log

        // Example using a coroutine for asynchronous call
        GlobalScope.launch(Dispatchers.IO) {
            try {

                // Call the authentication API
                val response = apiService.authenticateUser(requestBody)
                Log.d("APIBS", response.toString())

                // Handle the authentication response
                handleAuthenticationResponse(response)
            } catch (e: Exception) {
                // Handle exceptions
                e.printStackTrace()

                runOnUiThread {
                    Toast.makeText(applicationContext, "Authentication Failed!", Toast.LENGTH_SHORT).show()
                    Log.e("APIBS", "Authentication Failed! Error: ${e.message}")
                }
            }
        }
    }

    interface ApiService {
        @POST("login")
        @Headers("Content-Type: application/json")
        suspend fun authenticateUser(@Body requestBody: AuthenticationRequestBody): AuthenticationResponse

    }

    private fun handleAuthenticationResponse(response: AuthenticationResponse) {
        // Perform actions based on the authentication response
        // For example, store tokens, navigate to the main activity, etc.
        runOnUiThread {
            // Update UI or navigate to the next screen

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_to_the_right, R.anim.slide_to_the_left)
        }
    }

    data class AuthenticationRequestBody(
        val email: String,
        val password: String,
    )

    data class AuthenticationResponse(
        val access_token: String,
        val user: Map<String, Any>

    )

// <--- END --->

}

