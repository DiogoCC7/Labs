package com.example.labs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
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