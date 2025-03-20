package com.example.a01_20210778

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.a01_20210778.data.Book
import com.example.a01_20210778.databinding.ActivityBookDetailBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class BookDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityBookDetailBinding
    private lateinit var tmapRepository: TmapRepository
    private lateinit var storeRepository: StoreRepositroy

    private var model: BookData? = null
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tmapService = TmapService(applicationContext)
        tmapRepository = TmapRepository(tmapService)
        val storeService = StoreService(applicationContext)
        storeRepository = StoreRepositroy(storeService)

        model = intent.getParcelableExtra("searchBook")

        renderView()

        val bookApplication = applicationContext as BookApplication
        val bookRepo = bookApplication.bookRepo

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.storeRv.layoutManager = layoutManager

        val adapter = StoreAdapter()

        lifecycleScope.launch {
            try {
                val storeList = storeRepository.getStores(model?.isbn13.toString())
                Log.d("BookDetail",model?.isbn13.toString())
                if (storeList != null) {
                    adapter.store = storeList
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@BookDetailActivity, "매장 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@BookDetailActivity, "데이터를 가져오는 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        adapter.setOnItemClickListener(object : StoreAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val keyword = "알라딘 중고서점 ${adapter.keyword}"
                Log.d("Activity", "Selected store: $keyword")

                if (keyword.isNotEmpty()) {
                    lifecycleScope.launch {
                        try {
                            val poiInfo = tmapService.getInfo(keyword)
                            if (poiInfo != null) {
                                // 위도와 경도를 Double로 변환
                                val lat = poiInfo.frontLat.toDouble()
                                val lon = poiInfo.frontLon.toDouble()


                                val location = LatLng(lat, lon)
                                googleMap.clear()
                                googleMap.addMarker(MarkerOptions().position(location).title(keyword))
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
                            } else {
                                Toast.makeText(this@BookDetailActivity, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Toast.makeText(this@BookDetailActivity, "검색 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this@BookDetailActivity, "키워드를 선택해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.storeRv.adapter = adapter

        binding.recommendBookStoreBtn.setOnClickListener {
            val title = model?.title.orEmpty()
            val author = model?.author.orEmpty()
            val publisher = model?.publisher.orEmpty()
            val description = model?.description.orEmpty()
            val isbn = model?.isbn13.orEmpty()
            val cover = model?.coverSmallUrl.orEmpty()

            val book = Book(0, title, author, publisher, description, isbn, cover)

            lifecycleScope.launch {
                bookRepo.addBook(book)
                Toast.makeText(this@BookDetailActivity, "${title}이 보관함에 저장되었습니다", Toast.LENGTH_SHORT).show()
            }
        }


        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun renderView() {
        binding.recommendBookTitleTv.text = model?.title.orEmpty()
        binding.recommendBookAuthorTv.text = model?.author.orEmpty()
        binding.recommendBookPublisherContentTv.text = model?.publisher.orEmpty()
        binding.recommendBookIntroduceContentTv.text = model?.description.orEmpty()
        binding.recommendBookISBNContentTv.text = model?.isbn13.orEmpty()

        Glide.with(binding.recommendBookImg.context)
            .load(model?.coverSmallUrl.orEmpty())
            .centerCrop()
            .into(binding.recommendBookImg)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val initialLocation = LatLng(37.5665, 126.9780) // 서울
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 10f))
    }
}
