package com.example.a01_20210778

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a01_20210778.data.Book
import com.example.a01_20210778.databinding.ActivityMainBinding
import com.example.a01_20210778.ui.StorageViewModel
import com.example.a01_20210778.ui.StorageViewModelFactory
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var timer: Timer? = null
    private val handler = Handler(Looper.getMainLooper())

    val bookViewModel: StorageViewModel by viewModels {

        StorageViewModelFactory(  (application as BookApplication).bookRepo )
    }  //내가 만든 뷰모델 + 뷰모델이 가지고 있는 원래 기능들 해서 완전해짐


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.studySearchIcon.setOnClickListener {
            Log.d("Main", "Search Icon Clicked")
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }


        binding.studyMyStorageIcon.setOnClickListener {
            Log.d("Main", "Storage Icon Clicked")
            val intent = Intent(this, MyStorageActivity::class.java)
            startActivity(intent)
        }
        binding.studyRecommendIcon.setOnClickListener {
            Log.d("Main", "BestSeller Icon Clicked")
            val intent = Intent(this, BestSellerActivity::class.java)
            startActivity(intent)
        }

        val adapter = StorageSelect4Adapter(ArrayList<Book>())

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.studyMyStorageRv.layoutManager = layoutManager
        binding.studyMyStorageRv.adapter = adapter


        bookViewModel.top4Books.observe(this, { books ->
            adapter.books = books
            adapter.notifyDataSetChanged()
        })

        val recommendAdapter = StudyVpAdapter(this)
        binding.studyRecommendVp.adapter = recommendAdapter
         binding.studyRecommendIndicator.setViewPager(binding.studyRecommendVp)

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin).toFloat()
        val pageOffsetPx = resources.getDimensionPixelOffset(R.dimen.offset).toFloat()

        binding.studyRecommendVp.setPageTransformer { page, position ->
            val offset = position * -(2 * pageOffsetPx + pageMarginPx)
            page.translationX = offset
        }

        // ViewPager2 내부의 RecyclerView에 패딩을 추가하여 중앙 정렬 효과
        val recyclerView = binding.studyRecommendVp.getChildAt(0) as RecyclerView
        val padding = (pageMarginPx + pageOffsetPx).toInt()
        recyclerView.setPadding(padding, 0, padding, 0)
        recyclerView.clipToPadding = false

        startAutoSlide(recommendAdapter)

    }

    private fun startAutoSlide(adapter: StudyVpAdapter) {
        // 새 Timer 객체 생성
        timer?.cancel()
        timer = Timer()
        timer?.scheduleAtFixedRate(3000, 3000) {
            handler.post {
                val nextItem = binding.studyRecommendVp.currentItem + 1
                if (nextItem < adapter.itemCount) {
                    binding.studyRecommendVp.currentItem = nextItem
                } else {
                    binding.studyRecommendVp.currentItem = 0 // 마지막 페이지에서 첫 페이지로 순환
                }
            }
        }
    }

}
