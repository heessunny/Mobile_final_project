package com.example.a01_20210778

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a01_20210778.databinding.ItemBookListBinding


class SearchBookAdapter : RecyclerView.Adapter<SearchBookAdapter.BookHolder>() {
    var books: List<BookData>? = null

    override fun getItemCount(): Int {
        return books?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val itemBinding =
            ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.itemBinding.itemBookListTitleTv.text = books?.get(position)?.title.toString()

        holder.itemBinding.itemBookListAuthorTv.text = books?.get(position)?.author.toString()

        Glide.with(holder.itemBinding.itemBookListCoverIv.context)
            .load(books?.get(position)?.coverSmallUrl.orEmpty())
            .into(holder.itemBinding.itemBookListCoverIv)

        holder.itemBinding.bookList.setOnClickListener {
            clickListener?.onItemClick(it, position)
        }
    }

    class BookHolder(val itemBinding: ItemBookListBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    var clickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }

}
