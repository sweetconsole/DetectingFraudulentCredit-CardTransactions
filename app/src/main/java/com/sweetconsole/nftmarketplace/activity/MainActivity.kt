package com.sweetconsole.nftmarketplace.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Picasso
import com.sweetconsole.nftmarketplace.R
import com.sweetconsole.nftmarketplace.adapters.AuthorsBlockAdapter
import com.sweetconsole.nftmarketplace.classes.Author
import com.sweetconsole.nftmarketplace.classes.Painting

class MainActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    private val paintings = mutableListOf<String>()
    private val authors = mutableListOf<Author>()
    private var randomPainting: String = ""
    private val url = "https://i.ibb.co/nrwrMk0/starry-night.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nftImage: ImageView = findViewById(R.id.nftImage)
        val nftName: TextView = findViewById(R.id.nftName)
        val nftAuthorImage: ImageView = findViewById(R.id.nftAuthorAvatar)
        val nftAuthorName: TextView = findViewById(R.id.nftAuthorName)

        val topAuthorsItemList: RecyclerView = findViewById(R.id.topCreatorsItems)

        val viewRankingsButton: LinearLayout = findViewById(R.id.viewRankingsButton)

        db.collection("paintings")
            .get()
            .addOnSuccessListener {result ->
                for (document in result) {
                    paintings.add(document.id)
                }

                randomIntroNFT(paintings.random(), nftImage, nftName, nftAuthorImage, nftAuthorName)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed Get Data", Toast.LENGTH_SHORT).show()
            }

        db.collection("authors")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    authors.add(document.toObject(Author::class.java))
                }

                topAuthorsItemList.layoutManager = LinearLayoutManager(this)
                topAuthorsItemList.adapter = AuthorsBlockAdapter(authors.sortedByDescending { it.sales }, this)
            }

        viewRankingsButton.setOnClickListener {
            startActivity(Intent(this, RankingsActivity::class.java))
        }
    }

    private fun randomIntroNFT(
        paintingId: String,
        image: ImageView,
        name: TextView,
        authorImage: ImageView,
        authorName: TextView
        ) {

        db.collection("paintings").document(paintingId)
            .get()
            .addOnSuccessListener {result ->
                val painting = result.toObject(Painting::class.java)

                if (painting != null) {
                    name.text = painting.name

                    Picasso.get()
                        .load(painting.url)
                        .error(R.drawable.category)
                        .into(image)

                    db.collection("authors").document(painting.author)
                        .get()
                        .addOnSuccessListener {authorData ->
                            val author = authorData.toObject(Author::class.java)

                            if (author != null) {
                                authorName.text = author.name

                                Picasso.get()
                                    .load(author.photo)
                                    .error(R.drawable.category)
                                    .into(authorImage)
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Failed Get Data", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed Get Data", Toast.LENGTH_SHORT).show()
            }


    }
}