package com.example.hydrated

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var buttonStart : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStart = findViewById(R.id.btnStart)

        buttonStart.setOnClickListener {
            val startIntent : Intent
            startIntent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(startIntent)
        }
    }
}