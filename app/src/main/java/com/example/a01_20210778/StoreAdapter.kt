package com.example.a01_20210778

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a01_20210778.data.Book
import com.example.a01_20210778.databinding.ItemBookListBinding
import com.example.a01_20210778.databinding.ItemStoreListBinding

class StoreAdapter : RecyclerView.Adapter<StoreAdapter.StoreHolder>() {

    var store: List<StoreData>? = null
    var keyword: String? = null

    // 클릭된 아이템의 위치를 저장
    private var selectedPosition: Int = RecyclerView.NO_POSITION

    override fun getItemCount(): Int {
        return store?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreHolder {
        val itemBinding =
            ItemStoreListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StoreHolder, position: Int) {
        val storeName = store?.get(position)?.name.toString()
        holder.itemBinding.itemStoreListTitleTv.text = storeName


        val currentPosition = holder.adapterPosition


        if (currentPosition == selectedPosition) {
            holder.itemBinding.itemStoreListTitleTv.setTypeface(null, Typeface.BOLD)

        } else {
            holder.itemBinding.itemStoreListTitleTv.setTypeface(null, Typeface.NORMAL)

        }

        holder.itemBinding.storeList.setOnClickListener {
            keyword = store?.get(currentPosition)?.name
            val previousPosition = selectedPosition
            selectedPosition = currentPosition

            // 아이템 상태 변경
            notifyItemChanged(previousPosition) // 이전 아이템 갱신
            notifyItemChanged(selectedPosition) // 새로 선택된 아이템 갱신

            // 클릭 리스너 호출
            clickListener?.onItemClick(it, currentPosition)
        }
    }

    class StoreHolder(val itemBinding: ItemStoreListBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    var clickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }
}
