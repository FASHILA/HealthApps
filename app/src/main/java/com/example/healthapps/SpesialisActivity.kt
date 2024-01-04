package com.example.healthapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpesialisActivity : AppCompatActivity() {
    private lateinit var rvSpesialis: RecyclerView
    private val list = ArrayList<Spesialis>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spesialis)
        rvSpesialis = findViewById(R.id.rv_spesialis)
        rvSpesialis.setHasFixedSize(true)

        list.addAll(listSpesialis)
        showRecyclerList()

    }

    private val listSpesialis: ArrayList<Spesialis>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataPhoto = resources.getStringArray(R.array.data_photo)
            val listSpesialis = ArrayList<Spesialis>()
            for (i in dataName.indices) {
                val spesialis = Spesialis(dataName[i], dataDescription[i], dataPhoto[i])
                listSpesialis.add(spesialis)
            }
            return listSpesialis
        }

    private fun showRecyclerList() {
        rvSpesialis.layoutManager = LinearLayoutManager(this)
        val listSpesialisAdapter = ListAdapterSpesialis(list)
        rvSpesialis.adapter = listSpesialisAdapter

        listSpesialisAdapter.setOnItemClickCallback(object : ListAdapterSpesialis.OnItemClickCallback {
            override fun onItemClicked(data: Spesialis) {
                showSelectedHero(data)
            }
        })

        listSpesialisAdapter.setOnItemClickCallback(object : ListAdapterSpesialis.OnItemClickCallback {
            override fun onItemClicked(data: Spesialis) {
                // Panggil method untuk menampilkan detail dengan mengirim data Hero yang sesuai
                showDetailActivity(data)

            }
        })

    }

    private fun showSelectedHero(spesialis: Spesialis) {
        Toast.makeText(this, "Kamu memilih " + spesialis.name, Toast.LENGTH_SHORT).show()
    }
    private fun showDetailActivity(spesialis: Spesialis) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("SPESIALIS_DATA", spesialis) // Mengirim data Hero ke DetailActivity
        startActivity(intent)
    }

}

