package com.sweetconsole.nftmarketplace

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Picasso
import com.sweetconsole.nftmarketplace.classes.Author
import com.sweetconsole.nftmarketplace.classes.Painting
import java.util.concurrent.TimeUnit

fun randomNFT (
    activity: AppCompatActivity,
    image: ImageView,
    name: TextView,
    authorImage: ImageView,
    authorName: TextView
) {
    val db = Firebase.firestore
    val paintings = mutableListOf<String>()

    db.collection("paintings")
        .get()
        .addOnSuccessListener {result ->
            for (document in result) {
                paintings.add(document.id)
            }

            db.collection("paintings").document(paintings.random())
                .get()
                .addOnSuccessListener {data ->
                    val painting = data.toObject(Painting::class.java)

                    if (painting != null) {
                        name.text = painting.name

                        Picasso.get()
                            .load(painting.url)
                            .error(R.drawable.error_load_image)
                            .into(image)

                        db.collection("authors").document(painting.author)
                            .get()
                            .addOnSuccessListener {authorData ->
                                val author = authorData.toObject(Author::class.java)

                                if (author != null) {
                                    authorName.text = author.name

                                    Picasso.get()
                                        .load(author.photo)
                                        .error(R.drawable.error_load_image)
                                        .into(authorImage)
                                }
                            }
                            .addOnFailureListener {
                                Toast.makeText(activity, "Failed Get Data", Toast.LENGTH_SHORT).show()
                            }

                    }
                }

        }
        .addOnFailureListener {
            Toast.makeText(activity, "Failed Get Data", Toast.LENGTH_SHORT).show()
        }
}