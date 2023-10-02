package com.example.submit_andro_lagi

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.submit_andro_lagi.MainActivity.Companion.INTENT_PARCELABLE

class DetailActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //val superhero = intent.getParcelableExtra<Pemain>(MainActivity.INTENT_PARCELABLE)
        val pemain = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Pemain>(INTENT_PARCELABLE, Pemain::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Pemain>(INTENT_PARCELABLE)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name= findViewById<TextView>(R.id.tv_item_name)
        val description= findViewById<TextView>(R.id.tv_item_description)
        val photo= findViewById<ImageView>(R.id.img_item_photo)

        photo.setImageResource(pemain?.photo!!)
        name.text = pemain.name
        description.text =pemain.description

    }

    override fun onSupportNavigateUp(): Boolean{
        onBackPressed()
        return true
    }
}