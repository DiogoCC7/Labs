package com.example.labs

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val buttonClick = findViewById<Button>(R.id.btnGoToAct1);

        val name = intent.getStringExtra("name");
        var studentNumber = intent.getStringExtra("studentNumber");

        if (studentNumber != null) {
            studentNumber = "nº $studentNumber"
        }

        findViewById<TextView>(R.id.textView1).apply {
            "${ name ?: "Not Defined" } ${studentNumber ?: "Number not Defined"}".also { text = it }
        }

        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);

            startActivity(intent);
            finishActivity(0);
        }
    }
}