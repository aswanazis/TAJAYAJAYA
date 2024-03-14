package com.example.tajayajaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tajayajaya.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginbtn.setOnClickListener {
            startActivity(Intent(this, FirstpageActivity::class.java))
        }
        binding.btnback.setOnClickListener {
            startActivity(Intent(this, FirstpageActivity::class.java))
        }
    }
}
