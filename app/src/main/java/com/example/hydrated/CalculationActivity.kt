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
import android.widget.EditText
import com.example.hydrated.models.PersonalInfo
import com.google.firebase.database.FirebaseDatabase

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
            val name = findViewById<EditText>(R.id.etName).text.toString().trim()
            val gender = genderSpinnerID.selectedItem.toString()
            val activity = activitySpinnerID.selectedItem.toString()
            val weightStr = findViewById<EditText>(R.id.editTextText).text.toString().trim()
            val weightUnit = weightSpinnerID.selectedItem.toString()

            if (name.isEmpty() || weightStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var weight = weightStr.toDouble()

            // Convert lbs → kg if needed
            if (weightUnit == "lbs") {
                weight *= 0.453592
            }

            // Calculate hydration goal in ml
            val goal = when (activity) {
                "Sedentary" -> (weight * 30).toInt()
                "Lightly Active" -> (weight * 35).toInt()
                "Moderately Active" -> (weight * 40).toInt()
                "Very Active" -> (weight * 45).toInt()
                else -> (weight * 30).toInt()
            }

            val personalInfo = PersonalInfo(name, gender, activity, weight, "kg", goal)

            // Save to Firebase
            val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val emailKey = sharedPref.getString("emailKey", null)

            if (emailKey != null) {
                val database = FirebaseDatabase.getInstance().getReference("users")
                database.child(emailKey).child("personal").setValue(personalInfo)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Goal saved: $goal ml/day", Toast.LENGTH_SHORT).show()

                        val homeIntent = Intent(this@CalculationActivity, HomeActivity::class.java)
                        homeIntent.putExtra("startFragment", "home")
                        startActivity(homeIntent)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "User not found in SharedPrefs", Toast.LENGTH_SHORT).show()
            }
        }

    }
}