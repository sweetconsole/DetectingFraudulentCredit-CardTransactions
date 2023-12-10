package com.sweetconsole.nftmarketplace.adapters

import android.content.Context
import android.content.Intent
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
import com.sweetconsole.nftmarketplace.activity.PaintingActivity
import com.sweetconsole.nftmarketplace.classes.Author

class TopAuthorPageAdapter (
    private var authors: List<Author>,
    private var context: Context
) : RecyclerView.Adapter<TopAuthorPageAdapter.AuthorsPageHolder>() {

    class AuthorsPageHolder(view: View): RecyclerView.ViewHolder(view) {
        val topAuthorItem: ConstraintLayout = view.findViewById(R.id.topAuthorItemPage)
        val topAuthorImage: ImageView = view.findViewById(R.id.topAuthorItemPageImage)
        val topAuthorRank: TextView = view.findViewById(R.id.topAuthorItemPageRank)
        val topAuthorName: TextView = view.findViewById(R.id.topAuthorItemPageName)
        val topAuthorSales: TextView = view.findViewById(R.id.topAuthorItemPageTotalSalesValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorsPageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_authors_page_item, parent, false)

        return AuthorsPageHolder(view)
    }

    override fun getItemCount(): Int {
        return authors.count()
    }

    override fun onBindViewHolder(holder: AuthorsPageHolder, position: Int) {
        val rank: Int = position + 1
        val sales: String = "${authors[position].sales} ETH"

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