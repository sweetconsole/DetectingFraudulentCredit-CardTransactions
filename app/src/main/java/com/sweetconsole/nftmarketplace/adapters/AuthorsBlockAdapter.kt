package com.sweetconsole.nftmarketplace.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sweetconsole.nftmarketplace.R
import com.sweetconsole.nftmarketplace.classes.Author

class AuthorsBlockAdapter (
    private var authors: List<Author>,
    private var context: Context
) : RecyclerView.Adapter<AuthorsBlockAdapter.AuthorBlockHolder>() {

    class  AuthorBlockHolder(view: View): RecyclerView.ViewHolder(view) {
        val topAuthorItem: ConstraintLayout = view.findViewById(R.id.topAuthorItemBlock)
        val topAuthorImage: ImageView = view.findViewById(R.id.topAuthorItemBlockImage)
        val topAuthorRank: TextView = view.findViewById(R.id.topAuthorItemBlockRank)
        val topAuthorName: TextView = view.findViewById(R.id.topAuthorItemBlockName)
        val topAuthorSales: TextView = view.findViewById(R.id.topCreatorsItemTotalSalesValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorBlockHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_authors_block_item, parent, false)

        return AuthorBlockHolder(view)
    }

    override fun getItemCount(): Int {
        return authors.count()
    }

    override fun onBindViewHolder(holder: AuthorBlockHolder, position: Int) {
        val rank: Int = position + 1

        Picasso.get()
            .load(authors[position].photo)
            .error(R.drawable.category)
            .into(holder.topAuthorImage)

        holder.topAuthorRank.text = rank.toString()
        holder.topAuthorName.text = authors[position].name
        holder.topAuthorSales.text = "${authors[position].sales} ETH"

        holder.topAuthorItem.setOnClickListener {
            Log.i("INFO", "Yes")
        }
    }
}
