package com.sweetconsole.nftmarketplace

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.sweetconsole.nftmarketplace.adapters.TopAuthorPageAdapter
import com.sweetconsole.nftmarketplace.adapters.TopAuthorsBlockAdapter
import com.sweetconsole.nftmarketplace.classes.Author

fun setTopAuthorsBlockItemsList(
    context: AppCompatActivity,
    itemList: RecyclerView,
    limit: Long
) {
    val db = Firebase.firestore
    val authors = mutableListOf<Author>()

    db.collection("authors")
        .limit(limit)
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                authors.add(document.toObject(Author::class.java))
            }

            itemList.layoutManager = LinearLayoutManager(context)
            itemList.adapter = TopAuthorsBlockAdapter(authors.sortedByDescending{ it.sales }, context)
        }
        .addOnFailureListener {
            Toast.makeText(context, "Error load authors", Toast.LENGTH_SHORT).show()
        }
}

fun setTopAuthorsPageItemsList(
    context: AppCompatActivity,
    itemList: RecyclerView,
    limit: Long
) {
    val db = Firebase.firestore
    val authors = mutableListOf<Author>()

    db.collection("authors")
        .limit(limit)
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                authors.add(document.toObject(Author::class.java))
            }

            itemList.layoutManager = LinearLayoutManager(context)
            itemList.adapter = TopAuthorPageAdapter(authors.sortedByDescending{ it.sales }, context)
        }
        .addOnFailureListener {
            Toast.makeText(context, "Error load authors", Toast.LENGTH_SHORT).show()
        }
}