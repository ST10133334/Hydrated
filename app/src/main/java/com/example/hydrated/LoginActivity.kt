package com.example.hydrated

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hydrated.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textRegister.setOnClickListener {
            val registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

        binding.homeBtnLogin.setOnClickListener {
            val calculationIntent = Intent(this@LoginActivity, CalculationActivity::class.java)
            startActivity(calculationIntent)
        }
    }
}
