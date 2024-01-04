package com.example.healthapps
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnLogin: ImageView
    private lateinit var btnRegister: Button
    private lateinit var txtRegister: TextView


    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        btnLogin = findViewById(R.id.buttonLogin)


        val txtRegister: TextView = findViewById(R.id.txtRegister)

        // Menambahkan event click pada TextView
        txtRegister.setOnClickListener {
            // Aksi yang ingin dilakukan saat TextView diklik, misalnya, membuka Activity Register
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Inisialisasi Firebase Database
        databaseReference = FirebaseDatabase.getInstance().reference

        btnLogin.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Isi semua kolom", Toast.LENGTH_SHORT).show()
            }
        }

        // Tambahkan logika untuk tombol register jika diperlukan
    }

    private fun loginUser(email: String, password: String) {
        // Membaca data dari Realtime Database
        databaseReference.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        if (user?.email == email && user.password == password) {
                            // Login berhasil
                            Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)

                            // Selesai dan kembali ke LoginActivity
                            finish()
                            // Tambahkan navigasi atau tindakan yang diperlukan setelah login berhasil
                            return
                        }
                    }
                    // Jika tidak ada akun yang sesuai
                    Toast.makeText(this@LoginActivity, "Login gagal. Cek email dan password.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Toast.makeText(this@LoginActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
