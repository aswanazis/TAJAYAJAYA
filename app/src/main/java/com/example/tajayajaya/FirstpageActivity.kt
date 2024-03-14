package com.example.tajayajaya

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tajayajaya.databinding.ActivityFirstpageBinding
import com.example.tajayajaya.databinding.ActivityMainBinding

class FirstpageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonlogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.btnreg1.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}