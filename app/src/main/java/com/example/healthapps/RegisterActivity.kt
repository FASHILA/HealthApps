package com.example.healthapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnRegister: ImageView
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tryLoginTextView: TextView = findViewById(R.id.trylogin)

        // Tambahkan event OnClickListener
        tryLoginTextView.setOnClickListener {
            // Intent untuk membuka LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        btnRegister = findViewById(R.id.buttonRegister)

        // Menggunakan URL database yang benar
        database = FirebaseDatabase.getInstance().getReference("users")

        btnRegister.setOnClickListener {
            val username = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Ada Data Yang Masih Kosong!!", Toast.LENGTH_SHORT).show()
            } else {
                // Menyimpan data pengguna ke database
                val userReference = database.child(username)
                userReference.child("username").setValue(username)
                userReference.child("email").setValue(email)
                userReference.child("password").setValue(password)

                Toast.makeText(applicationContext, "Register Berhasil", Toast.LENGTH_SHORT).show()

                // Pindah ke halaman login setelah registrasi berhasil
                // Misalnya, Anda bisa menggunakan Intent untuk berpindah ke halaman Login
                 val loginIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                 startActivity(loginIntent)
            }
        }
    }
}
