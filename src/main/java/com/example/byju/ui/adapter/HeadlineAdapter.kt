package com.example.byju.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.byju.R
import com.example.byju.model.Articles
import com.example.byju.ui.DetailActivity


class HeadlineAdapter : RecyclerView.Adapter<HeadlineAdapter.ViewHolder>() {
    var headlineList = mutableListOf<Articles>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_headlines, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return headlineList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val headline = headlineList[position]
        holder.headLines.text = headline.title
        val options = RequestOptions().centerCrop()
        Glide.with(holder.image).load(headline.urlToImage)
            .apply(options).into(holder.image)
        holder.sourceTv.text = headline.source?.name
        holder.timeTv.text = headline.publishedAt
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        override fun onClick(v: View?) {
            val intent = DetailActivity.getIntent(v?.context,headlineList[adapterPosition])
            v?.context?.startActivity(intent)
        }

        val image: ImageView = view.findViewById(R.id.news_image)
        val headLines: TextView = view.findViewById(R.id.headlinesTv)
        val sourceTv: TextView = view.findViewById(R.id.sourceTv)
        val timeTv: TextView = view.findViewById(R.id.timeTv)

        init {
            view.setOnClickListener(this)
        }
    }
}