package com.sweetconsole.nftmarketplace.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.sweetconsole.nftmarketplace.R
import com.sweetconsole.nftmarketplace.randomNFT
import com.sweetconsole.nftmarketplace.setTopAuthorsItemsList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val introBlock: ConstraintLayout = findViewById(R.id.introBlock)
        val introBlockImage: ImageView = findViewById(R.id.nftImage)
        val introBlockName: TextView = findViewById(R.id.nftName)
        val introBlockAuthorImage: ImageView = findViewById(R.id.nftAuthorAvatar)
        val introBlockAuthorName: TextView = findViewById(R.id.nftAuthorName)

        val topAuthorsItemList: RecyclerView = findViewById(R.id.topCreatorsItems)
        val viewRankingsButton: LinearLayout = findViewById(R.id.viewRankingsButton)

        val discoverMoreNFTItemList: RecyclerView = findViewById(R.id.discoverMoreNFTItems)

        randomNFT(this, introBlockImage, introBlockName, introBlockAuthorImage, introBlockAuthorName)
        setTopAuthorsItemsList(this, topAuthorsItemList, 5, "Block")

        introBlock.setOnClickListener {
            startActivity(Intent(this, PaintingActivity::class.java))
        }

        viewRankingsButton.setOnClickListener {
            startActivity(Intent(this, RankingsActivity::class.java))
        }
    }
}