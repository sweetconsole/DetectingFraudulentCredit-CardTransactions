package com.sweetconsole.nftmarketplace

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.sweetconsole.nftmarketplace.adapters.TopAuthorsAdapter
import com.sweetconsole.nftmarketplace.classes.Author

fun setTopAuthorsItemsList(
    context: AppCompatActivity,
    itemList: RecyclerView,
    limit: Long,
    type: String
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
            itemList.adapter = TopAuthorsAdapter(authors.sortedByDescending{ it.sales }, context, type)
        }
        .addOnFailureListener {
            Toast.makeText(context, "Error load authors", Toast.LENGTH_SHORT).show()
        }
}