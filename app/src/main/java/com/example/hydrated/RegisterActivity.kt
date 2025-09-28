package com.example.hydrated

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hydrated.databinding.ActivityLoginBinding
import com.example.hydrated.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textLogin.setOnClickListener {
            val registerIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(registerIntent)
        }

        binding.homeBtnRegister.setOnClickListener {
            val calculationIntent = Intent(this@RegisterActivity, CalculationActivity::class.java)
            startActivity(calculationIntent)
        }
    }
}