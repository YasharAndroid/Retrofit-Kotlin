package com.example.retrofit_kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_kotlin.databinding.PostsItemBinding

class PostsAdapter(var postList: ArrayList<Posts>) :
    RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    /**
     * PostItemBinding because of using viewBinding
     */
    inner class PostsViewHolder(val adapterBinding: PostsItemBinding) :
        RecyclerView.ViewHolder(adapterBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {

        val binding = PostsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PostsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {

        holder.adapterBinding.textViewUserId.text = postList[position].userId.toString()
        holder.adapterBinding.textViewId.text = postList[position].id.toString()
        holder.adapterBinding.textViewTitle.text = postList[position].title
        holder.adapterBinding.textViewBody.text = postList[position].subtitle
    }
}