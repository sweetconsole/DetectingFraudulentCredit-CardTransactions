package com.sweetconsole.nftmarketplace

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private val reference = Firebase.storage.reference.child("van-gogh/van-gogh-1.jpg")
    private val url = "https://gas-kvas.com/uploads/posts/2023-02/1675344318_gas-kvas-com-p-van-gog-art-risunok-37.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.nftImage)

        Picasso.get()
            .load(url)
            .error(R.drawable.category)
            .into(imageView)
    }
}