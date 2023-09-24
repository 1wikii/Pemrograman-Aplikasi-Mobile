package com.example.tugaspam.ui.skill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.tugaspam.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val skill = intent.getParcelableExtra<Skill>("skill")
        if(skill != null){
            val textView : TextView = findViewById(R.id.detailActivityTv)
            val imageView : ImageView = findViewById(R.id.detailActivityIv)

            textView.text = skill.heading
            imageView.setImageResource(skill.titleImage)
        }

    }
}