package com.example.healthapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSpesialis: ImageView = findViewById(R.id.buttonSpesialis)
        buttonSpesialis.setOnClickListener {
            // Buat Intent untuk membuka SpesialisActivity
            val intent = Intent(this@MainActivity, SpesialisActivity::class.java)

            startActivity(intent)
        }

        val buttonEdukasi: ImageView = findViewById(R.id.buttonEdukasi)

        buttonEdukasi.setOnClickListener {
            // Ketika ImageView diklik, buat Intent untuk membuka EducationActivity
            val intent = Intent(this@MainActivity, EducationActivity::class.java)
            startActivity(intent)
        }
    }
}