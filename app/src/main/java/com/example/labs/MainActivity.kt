package com.example.labs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClick = findViewById<Button>(R.id.btnGoToAct2)
        val crudButton = findViewById<Button>(R.id.btnGoToAct3)

        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("name", "Diogo Assunção");
                putExtra("studentNumber", "28257")
            }

            startActivity(intent)
        }

        crudButton.setOnClickListener {
            val intent = Intent(this, CrudActivity::class.java).apply {
            }

            startActivity(intent)
        }
    }
}