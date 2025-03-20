package com.example.a01_20210778

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.a01_20210778.databinding.FragmentVp5Binding
import kotlinx.coroutines.launch


class Vp5Fragment : Fragment() {

    lateinit var binding: FragmentVp5Binding
    private lateinit var bestSellerRepository: BestSellerRepository



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVp5Binding.inflate(inflater, container, false)
        bestSellerRepository = BestSellerRepository(BestSellerService(requireContext()))


        loadBookAtIndex(4)

        return binding.root
    }

    private fun loadBookAtIndex(index: Int) {
        lifecycleScope.launch {
            try {
                val book = bestSellerRepository.getBookAtIndex(index)
                if (book != null) {
                    // 책 데이터를 UI에 표시
                    Glide.with(requireContext())
                        .load(book.coverSmallUrl)  // 이미지 URL
                        .centerCrop()
                        .into(binding.imgBanner5)


                    binding.imgBanner5.setOnClickListener {
                        val intent = Intent(requireContext(), BookDetailActivity::class.java).apply {
                            putExtra("searchBook", book) // BookData 객체 전달
                        }
                        startActivity(intent)
                    }
                } else {

                    Glide.with(requireContext())
                        .load("")
                        .centerCrop()
                        .into(binding.imgBanner5)
                }
            } catch (e: Exception) {

                e.printStackTrace()
                Glide.with(requireContext())
                    .load("")
                    .centerCrop()
                    .into(binding.imgBanner5)
            }
        }
    }
}