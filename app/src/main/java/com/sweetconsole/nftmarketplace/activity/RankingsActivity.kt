package com.sweetconsole.nftmarketplace.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.sweetconsole.nftmarketplace.R
import com.sweetconsole.nftmarketplace.setTopAuthorsItemsList

class RankingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rankings)

        val topCreatorsPageItems: RecyclerView = findViewById(R.id.topCreatorsPageItems)

        // App Bar Menu
        val buttonMenuHome: ImageButton = findViewById(R.id.MenuHome)

        setTopAuthorsItemsList(this, topCreatorsPageItems, 20, "Page")


        buttonMenuHome.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}