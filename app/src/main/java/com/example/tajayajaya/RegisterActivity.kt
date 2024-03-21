package com.example.tajayajaya

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Observable
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tajayajaya.databinding.ActivityLoginBinding
import com.example.tajayajaya.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.widget.RxTextView

@SuppressLint("CheckResult")
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

//  Auth
        auth = FirebaseAuth.getInstance()
//  Email Validation
        val emailStream = RxTextView.textChanges(binding.emailregis)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            showEmailValidAlert(it)
        }

//  Username Validation
        val usernameStream = RxTextView.textChanges(binding.username)
            .skipInitialValue()
            .map { username ->
                username.length < 6
            }
        usernameStream.subscribe {
            showTextMinimalAlert(it, "Username")
        }

//  Password Validation
        val passwordStream = RxTextView.textChanges(binding.passregis)
            .skipInitialValue()
            .map { password ->
                password.length < 6
            }
        passwordStream.subscribe {
            showTextMinimalAlert(it, "Password")
        }

// Confirm Password Validation
        val passwordConfirmStream = io.reactivex.Observable.merge(
            RxTextView.textChanges(binding.passregis)
                .skipInitialValue()
                .map { password ->
                    password.toString() != binding.confirmpass.text.toString()
                },
            RxTextView.textChanges(binding.confirmpass)
                .skipInitialValue()
                .map { confirmPassword ->
                    confirmPassword.toString() != binding.passregis.text.toString()
                })
        passwordConfirmStream.subscribe {
            showPasswordConfirmAlert(it)
        }

// Button Enable True or False
        val invalidFieldStream = io.reactivex.Observable.combineLatest(
            emailStream,
            usernameStream,
            passwordStream,
            passwordConfirmStream,
            {  emailInvalid: Boolean, usernameInvalid: Boolean, passwordInvalid: Boolean, passwordConfirmInvalid: Boolean ->
                !emailInvalid && !usernameInvalid && !passwordInvalid && !passwordConfirmInvalid
            })
        invalidFieldStream.subscribe { isValid ->
            if (isValid) {
                binding.btnregis.isEnabled = true
            }
        }
//  Click
        binding.btnregis.setOnClickListener {
            val email = binding.emailregis.text.toString().trim()
            val password = binding.passregis.text.toString().trim()
            registerUser(email, password)
        }
        binding.btnbackregis.setOnClickListener {
            startActivity(Intent(this, FirstpageActivity::class.java))
        }
    }

    private fun showEmailValidAlert(isNotValid: Boolean){
        binding.emailregis.error = if (isNotValid) "Email tidaak valid!" else null
    }
    private fun showTextMinimalAlert(isNotValid: Boolean, text: String){
        if (text == "Username")
            binding.username.error = if (isNotValid) "$text harus lebih dari 5 huruf" else null
        else if (text == "Password")
            binding.passregis.error = if (isNotValid) "$text harus lebih dari 6 huruf!" else null
    }
    private fun showPasswordConfirmAlert(isNotValid: Boolean) {
        binding.confirmpass.error = if (isNotValid) "Password tidak sama!" else null
    }

    private fun  registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    Toast.makeText(this, "Register berhasil!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}