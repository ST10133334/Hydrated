package com.example.hydrated

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Spinner
import android.widget.Toast

class CalculationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculation)

        // Gender
        val genderSpinnerID = findViewById<Spinner>(R.id.spGender)
        val genders = arrayOf("Male", "Female")
        val arrayAdp = ArrayAdapter(this@CalculationActivity, android.R.layout.simple_spinner_dropdown_item, genders)
        genderSpinnerID.adapter = arrayAdp

        // Activity
        val activitySpinnerID = findViewById<Spinner>(R.id.spActivity)
        val activities = arrayOf("Sedentary", "Lightly Active", "Moderately Active", "Very Active")
        val arrayAdp2 = ArrayAdapter(this@CalculationActivity, android.R.layout.simple_spinner_dropdown_item, activities)
        activitySpinnerID.adapter = arrayAdp2

        // Weight
        val weightSpinnerID = findViewById<Spinner>(R.id.spWeight)
        val weights = arrayOf("kg", "lbs")
        val arrayAdp3 = ArrayAdapter(this@CalculationActivity, android.R.layout.simple_spinner_dropdown_item, weights)
        weightSpinnerID.adapter = arrayAdp3

        // Calculate daily goal button
        val btnCalculate = findViewById<com.google.android.material.button.MaterialButton>(R.id.btnCalculate)
        btnCalculate.setOnClickListener {
            val homeIntent = Intent(this@CalculationActivity, HomeActivity::class.java)
            homeIntent.putExtra("startFragment", "home")
            startActivity(homeIntent)
        }
    }
}