package com.example.healthapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EducationActivity : AppCompatActivity() {

    private lateinit var rvEducation: RecyclerView
    private val list = ArrayList<Education>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)

        rvEducation = findViewById(R.id.rv_education)
        rvEducation.setHasFixedSize(true)

        list.addAll(listEducation)
        showRecyclerList()
    }

    private val listEducation: ArrayList<Education>
        get() {
            val dataName = resources.getStringArray(R.array.data_title)
            val dataDescription = resources.getStringArray(R.array.data_descriptionedu)
            val dataPhoto = resources.getStringArray(R.array.data_photoedu)
            val listEducation = ArrayList<Education>()
            for (i in dataName.indices) {
                val education = Education(dataName[i], dataDescription[i], dataPhoto[i])
                listEducation.add(education)
            }
            return listEducation
        }

    private fun showRecyclerList() {
        rvEducation.layoutManager = LinearLayoutManager(this)
        val listEducationAdapter = ListAdapterEducation(list)
        rvEducation.adapter = listEducationAdapter

        listEducationAdapter.setOnItemClickCallback(object : ListAdapterEducation.OnItemClickCallback {
            override fun onItemClicked(data: Education) {
                showSelectedHero(data)
            }
        })

        listEducationAdapter.setOnItemClickCallback(object : ListAdapterEducation.OnItemClickCallback {
            override fun onItemClicked(data: Education) {
                // Panggil method untuk menampilkan detail dengan mengirim data Hero yang sesuai
                showDetailActivity(data)

            }
        })

    }

    private fun showSelectedHero(edu : Education) {
        Toast.makeText(this, "Kamu memilih " + edu.name, Toast.LENGTH_SHORT).show()
    }
    private fun showDetailActivity(edu : Education) {
        val intent = Intent(this, DetailEduActivity::class.java)
        intent.putExtra("EDUCATION_DATA", edu) // Mengirim data Hero ke DetailActivity
        startActivity(intent)
    }

}

