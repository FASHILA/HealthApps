package com.example.healthapps


import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val janjiTemuButton: Button = findViewById(R.id.janji_temu)
        janjiTemuButton.setOnClickListener {
            // Saat tombol ditekan, tampilkan pesan Toast
            showToast("Janji Temu sudah dibuat. Silahkan tunggu informasi selanjutnya pada Email Anda.")
        }




        // Mendapatkan data Hero dari intent
        val spesialis = intent.getParcelableExtra<Spesialis>("SPESIALIS_DATA")

        // Menampilkan data Hero di tampilan detail
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val spesialisImageView = findViewById<ImageView>(R.id.heroImageView)

        // Tombol panah kembali
        val backButton = findViewById<Button>(R.id.action_back)
        backButton.setOnClickListener {
            onBackPressed()
        }

        titleTextView.text = spesialis?.name
        descriptionTextView.text = spesialis?.description

        // Menggunakan Glide untuk memuat gambar dari URL (hero.photo) dan menampilkannya
        spesialis?.photo?.let {
            Glide.with(this)
                .load(it)
                .into(spesialisImageView)
        }


        }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    }

