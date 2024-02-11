package com.sweetconsole.nftmarketplace.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.sweetconsole.nftmarketplace.R
import com.sweetconsole.nftmarketplace.setTopAuthorsItemsList

class RankingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rankings)

        val topCreatorsPageItems: RecyclerView = findViewById(R.id.topCreatorsPageItems)

        setTopAuthorsItemsList(this, topCreatorsPageItems, 20, "Page")
    }
}