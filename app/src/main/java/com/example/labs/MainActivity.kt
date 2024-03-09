package com.example.labs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        val buttonClick = findViewById<Button>(R.id.btnGoToAct2)
        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("name", "Diogo Assunção");
                putExtra("studentNumber", "28257")
            }

            startActivity(intent)
        }
    }
}