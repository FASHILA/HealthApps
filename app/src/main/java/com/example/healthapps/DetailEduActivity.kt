package com.example.healthapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class DetailEduActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_edu)

        // Mendapatkan data Hero dari intent
        val education = intent.getParcelableExtra<Education>("EDUCATION_DATA")

        // Menampilkan data Hero di tampilan detail
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val eduImageView = findViewById<ImageView>(R.id.heroImageView)

        // Tombol panah kembali
        val backButton = findViewById<Button>(R.id.action_back)
        backButton.setOnClickListener {
            onBackPressed()
        }

        titleTextView.text = education?.name
        descriptionTextView.text = education?.description

        // Menggunakan Glide untuk memuat gambar dari URL (hero.photo) dan menampilkannya
        education?.photo?.let {
            Glide.with(this)
                .load(it)
                .into(eduImageView)
        }


    }

}