package com.example.assetfix.mobile.auth
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assetfix.R
import com.example.assetfix.mobile.dashboard.Dashboard
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.assetfix.R.layout.activity_signup_page)
        val haveAnAccount: TextView = findViewById(R.id.have_an_account)
        haveAnAccount.setOnClickListener {
            goToLogInPage()
        }
        val createAccount: MaterialButton = findViewById(R.id.signUp)
        createAccount.setOnClickListener {
            createNewAccount()
        }
    }

    private fun createNewAccount() {
        val userName: TextInputEditText = findViewById(R.id.userName_edit_text)
        val name = userName.text.toString()
        val userMail: TextInputEditText = findViewById(R.id.signUpMail_edit_text)
        val mail = userMail.text.toString()
        val userMobileNo: TextInputEditText = findViewById(R.id.phoneNo_edit_text)
        val phoneNo = userMobileNo.text.toString()
        val userOrganization: TextInputEditText = findViewById(R.id.organization_edit_text)
        val organization = userOrganization.text.toString()
        val userPassword: TextInputEditText = findViewById(R.id.password_edit_text)
        val password = userPassword.text.toString()
        val userConfirmPassword: TextInputEditText = findViewById(R.id.re_password_edit_text)
        val confirmPassword = userConfirmPassword.text.toString()

        var isAnyFieldEmpty = false

        // Validate each input field for empty string
        if (name.isEmpty()) {
            val nameLayout: TextInputLayout = findViewById(R.id.userName)
            nameLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            nameLayout.setBoxStrokeColor(Color.RED)
            nameLayout.setError("Please enter your Name")
            isAnyFieldEmpty = true
        }

        if (mail.isEmpty()) {
            val mailLayout: TextInputLayout = findViewById(R.id.signUpMail)
            mailLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            mailLayout.setBoxStrokeColor(Color.RED)
            mailLayout.setError("Please enter your Email")
            isAnyFieldEmpty = true
        }else    if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            // If email is invalid, show error message and set outline color to red
            userMail.error = "Please enter a valid email address"
            val emailLayout: TextInputLayout = findViewById(R.id.signUpMail)
            emailLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            emailLayout.setBoxStrokeColor(Color.RED)}

        if (phoneNo.isEmpty()) {
            val phoneNumberLayout: TextInputLayout = findViewById(R.id.phoneNo)
            phoneNumberLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            phoneNumberLayout.setBoxStrokeColor(Color.RED)
            phoneNumberLayout.setError("Please enter your Phone Number")
            isAnyFieldEmpty = true
        }

        if (organization.isEmpty()) {
            val organizationLayout: TextInputLayout = findViewById(R.id.organization)
            organizationLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            organizationLayout.setBoxStrokeColor(Color.RED)
            organizationLayout.setError("Please enter your Organization")
            isAnyFieldEmpty = true
        }

        if (password.isEmpty()) {
            val passwordLayout: TextInputLayout = findViewById(R.id.password)
            passwordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            passwordLayout.setBoxStrokeColor(Color.RED)
            passwordLayout.setError("Please enter your Password")
            isAnyFieldEmpty = true
        } else if(password.length<6){
            val passwordLayout: TextInputLayout = findViewById(R.id.password)
            passwordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            passwordLayout.setBoxStrokeColor(Color.RED)
            passwordLayout.setError("Your password must be 6 characters")
        }
        if (confirmPassword.isEmpty()) {
            val confirmPasswordLayout: TextInputLayout = findViewById(R.id.re_password)
            confirmPasswordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            confirmPasswordLayout.setBoxStrokeColor(Color.RED)
            confirmPasswordLayout.setError("Please confirm your Password")
            isAnyFieldEmpty = true
        } else if(confirmPassword.length<6){
            val confirmPasswordLayout: TextInputLayout = findViewById(R.id.password)
            confirmPasswordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            confirmPasswordLayout.setBoxStrokeColor(Color.RED)
            confirmPasswordLayout.setError("Your password must be 6 characters")
        }
        if (password!=confirmPassword){
            val confirmPasswordLayout: TextInputLayout = findViewById(R.id.re_password)
            confirmPasswordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            confirmPasswordLayout.setBoxStrokeColor(Color.RED)
            confirmPasswordLayout.setError("Passwords do not match")
        }
        else{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_to_the_right, R.anim.slide_to_the_left)
        }
    }
    private fun goToLogInPage() {
        val intent = Intent(this, LogInpage::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_from_the_left, R.anim.slide_out_of_the_page_right)
    }
}