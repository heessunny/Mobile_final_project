package com.example.a01_20210778

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a01_20210778.data.Book
import com.example.a01_20210778.databinding.ItemBookListBinding

class StorageSelect4Adapter (/*val*/ var books: List<Book>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding : ItemBookListBinding =ItemBookListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BookHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookHolder).onBind(books[position])
    }

    override fun getItemCount(): Int = books.size

    class BookHolder(val binding: ItemBookListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Book) {

            Glide.with(binding.root.context)
                .load(data.cover)  // 이미지 URL
                .centerCrop()
                .into(binding.itemBookListCoverIv)

            binding.itemBookListTitleTv.text=data.title
            binding.itemBookListAuthorTv.text=data.author
        }
    }
}