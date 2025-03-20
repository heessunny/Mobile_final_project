package com.example.a01_20210778

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.a01_20210778.databinding.FragmentVp2Binding
import kotlinx.coroutines.launch


class Vp2Fragment : Fragment() {

    lateinit var binding: FragmentVp2Binding
    private lateinit var bestSellerRepository: BestSellerRepository


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVp2Binding.inflate(inflater, container, false)

        bestSellerRepository = BestSellerRepository(BestSellerService(requireContext()))


        loadBookAtIndex(1)

        return binding.root
    }

    private fun loadBookAtIndex(index: Int) {
        lifecycleScope.launch {
            try {
                val book = bestSellerRepository.getBookAtIndex(index)
                if (book != null) {

                    Glide.with(requireContext())
                        .load(book.coverSmallUrl)
                        .centerCrop()
                        .into(binding.imgBanner2)


                    binding.imgBanner2.setOnClickListener {
                        val intent = Intent(requireContext(), BookDetailActivity::class.java).apply {
                            putExtra("searchBook", book)
                        }
                        startActivity(intent)
                    }
                } else {

                    Glide.with(requireContext())
                        .load("")
                        .centerCrop()
                        .into(binding.imgBanner2)
                }
            } catch (e: Exception) {

                e.printStackTrace()
                Glide.with(requireContext())
                    .load("")
                    .centerCrop()
                    .into(binding.imgBanner2)
            }
        }
    }
}