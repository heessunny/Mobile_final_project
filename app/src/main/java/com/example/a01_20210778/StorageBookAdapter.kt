package com.example.a01_20210778

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a01_20210778.data.Book
import com.example.a01_20210778.databinding.ItemStorageBooksBinding

class StorageBookAdapter (/*val*/ var books: List<Book>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding : ItemStorageBooksBinding = ItemStorageBooksBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GridItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GridItemViewHolder).onBind(books[position])
    }

    override fun getItemCount(): Int = books.size

    class GridItemViewHolder(val binding: ItemStorageBooksBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Book) {

            Glide.with(binding.root.context)
                .load(data.cover)  // 이미지 URL
                .centerCrop()
                .into(binding.itemStorageCoverImgIv)

            binding.itemStorageTitleTv.text=data.title
            binding.itemStorageAuthorTv.text=data.author
        }
    }
}