package com.example.submit_andro_lagi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object{
        val INTENT_PARCELABLE="OBJECT_INTENT"
    }

    private lateinit var rvPemain: RecyclerView
    private val list = ArrayList<Pemain>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        rvPemain = findViewById(R.id.rv_pemain)
        rvPemain.setHasFixedSize(true)

        list.addAll(getListPemain())
        showRecyclerList()

        /*val recyclerView = findViewById<RecyclerView> (R.id.rv_pemain)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize (true)
        recyclerView.adapter = ListPemainAdapter(this, list) {
            val intent = Intent (this, DetailActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }*/
        val btnAbout = findViewById<Button>(R.id.btnAbout)
        btnAbout.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvPemain.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvPemain.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListPemain(): ArrayList<Pemain> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listPemain = ArrayList<Pemain>()
        for (i in dataName.indices) {
            val pemain = Pemain(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listPemain.add(pemain)
        }
        return listPemain
    }

    private fun showRecyclerList() {
        rvPemain.layoutManager = LinearLayoutManager(this)
        val listPemainAdapter = ListPemainAdapter(list)
        rvPemain.adapter = listPemainAdapter

        // pengingat buat diri sendiri = ini jangan lupa di debug dlu
        listPemainAdapter.setOnItemClickCallback(object : ListPemainAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Pemain) {
                //showSelectedPemain(data)
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(INTENT_PARCELABLE, data)
                startActivity(intent)
            }
        })
    }

    private fun showSelectedPemain(pemain: Pemain) {
        Toast.makeText(this, "Kamu memilih " + pemain.name, Toast.LENGTH_SHORT).show()
    }


}