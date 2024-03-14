package com.example.tajayajaya

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tajayajaya.databinding.ActivityLoginBinding
import com.example.tajayajaya.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnregis.setOnClickListener {
            startActivity(Intent(this, FirstpageActivity::class.java))
        }
        binding.btnbackregis.setOnClickListener {
            startActivity(Intent(this, FirstpageActivity::class.java))
        }
    }
}