package com.sweetconsole.nftmarketplace.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Picasso
import com.sweetconsole.nftmarketplace.R
import com.sweetconsole.nftmarketplace.classes.Author
import com.sweetconsole.nftmarketplace.classes.Painting

class PaintingAdapter (
    private var painting: List<Painting>,
    private var context: Context
) : RecyclerView.Adapter<PaintingAdapter.PaintingHolder>() {

    class PaintingHolder(view: View): RecyclerView.ViewHolder(view) {
        val paintingImage: ImageView = view.findViewById(R.id.paintingItemImage)
        var paintingName: TextView = view.findViewById(R.id.paintingName)
        var paintingAuthorImage: ImageView = view.findViewById(R.id.paintingItemAuthorImage)
        var paintingAuthorName: TextView = view.findViewById(R.id.paintingItemAuthorName)
        var paintingPrice: TextView = view.findViewById(R.id.paintingItemPriceValue)
        var paintingBet: TextView = view.findViewById(R.id.paintingItemBetValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaintingHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.painting_item, parent, false)

        return PaintingHolder(view)
    }

    override fun getItemCount(): Int {
        return painting.count()
    }

    override fun onBindViewHolder(holder: PaintingHolder, position: Int) {
        val db = Firebase.firestore
        val price = "${painting[position].price} ETH"
        val bet = "${painting[position].bet} wETH"

        Picasso.get()
            .load(painting[position].url)
            .error(R.drawable.error_load_image)
            .into(holder.paintingImage)

        holder.paintingName.text = painting[position].name
        holder.paintingPrice.text = price
        holder.paintingBet.text = bet

        db.collection("authors").document(painting[position].author)
            .get()
            .addOnSuccessListener { data ->
                val author = data.toObject(Author::class.java)

                if (author != null) {
                    Picasso.get()
                        .load(author.photo)
                        .error(R.drawable.error_load_image)
                        .into(holder.paintingAuthorImage)

                    holder.paintingAuthorName.text = author.name
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed Get Data", Toast.LENGTH_SHORT).show()
                Log.e("ERROR", "Failed Get Data")
            }

    }
}