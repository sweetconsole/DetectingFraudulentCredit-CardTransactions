package com.sweetconsole.nftmarketplace.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sweetconsole.nftmarketplace.R
import com.sweetconsole.nftmarketplace.activity.PaintingActivity
import com.sweetconsole.nftmarketplace.classes.Author

class TopAuthorsAdapter (
    private var authors: List<Author>,
    private var context: Context,
    private var type: String
) : RecyclerView.Adapter<TopAuthorsAdapter.AuthorHolder>() {

    class AuthorHolder(view: View): RecyclerView.ViewHolder(view) {
        val topAuthorItem: ConstraintLayout = view.findViewById(R.id.topAuthorItem)
        val topAuthorImage: ImageView = view.findViewById(R.id.topAuthorImage)
        val topAuthorRank: TextView = view.findViewById(R.id.topAuthorRank)
        val topAuthorName: TextView = view.findViewById(R.id.topAuthorName)
        val topAuthorSales: TextView = view.findViewById(R.id.topAuthorSales)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorHolder {
        return when (type) {
            "Block" -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.top_authors_block_item, parent, false)

                AuthorHolder(view)
            }
            "Page" -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.top_authors_page_item, parent, false)

                AuthorHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.top_authors_block_item, parent, false)

                AuthorHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return authors.count()
    }

    override fun onBindViewHolder(holder: AuthorHolder, position: Int) {
        val rank = position + 1
        val sales = "${authors[position].sales} ETH"

        Picasso.get()
            .load(authors[position].photo)
            .error(R.drawable.error_load_image)
            .into(holder.topAuthorImage)

        holder.topAuthorRank.text = rank.toString()
        holder.topAuthorName.text = authors[position].name
        holder.topAuthorSales.text = sales

        holder.topAuthorItem.setOnClickListener {
            context.startActivity(Intent(context, PaintingActivity::class.java))
        }
    }
}
