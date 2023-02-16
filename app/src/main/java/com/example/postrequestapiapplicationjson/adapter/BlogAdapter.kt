package com.example.postrequestapiapplicationjson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postrequestapiapplicationjson.R
import com.example.postrequestapiapplicationjson.model.Placeholder

class BlogAdapter(private val context: Context): RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    private val blogs: MutableList<Placeholder> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(LayoutInflater.from(context).inflate(R.layout.item_blog, parent, false))
    }

    override fun getItemCount(): Int {
        return blogs.size
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bindModel(blogs[position])
    }

    fun setBlog(data: List<Placeholder>) {
        blogs.clear()
        blogs.addAll(data)
        notifyDataSetChanged()
    }

    inner class BlogViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val txtTitle: TextView = item.findViewById(R.id.tv_title)
        private val txtDesc: TextView = item.findViewById(R.id.tv_desc)

        fun bindModel(b: Placeholder) {
            txtTitle.text = b.title
            txtDesc.text = b.body
        }

    }


}