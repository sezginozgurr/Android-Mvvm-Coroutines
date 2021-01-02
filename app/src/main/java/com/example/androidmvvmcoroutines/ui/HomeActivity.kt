package com.example.androidmvvmcoroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.androidmvvmcoroutines.R
import com.example.androidmvvmcoroutines.adapter.BaseAdapter
import com.example.androidmvvmcoroutines.databinding.ActivityMainBinding
import com.example.androidmvvmcoroutines.data.response.NewsResponse
import com.example.androidmvvmcoroutines.vm.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var newsList: NewsResponse? = null
    private val newsViewModel: HomeViewModel by viewModels()
    //private lateinit var viewModel: HomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        newsViewModel.request.observe(this, {
            newsList = it
            setAdapter()
        })

    }

    private fun setAdapter() {
        binding.recyclerNews.apply {
            adapter = BaseAdapter(
                this@HomeActivity,
                R.layout.row_item_news,
                newsList?.articles
            ) { v, item, position ->
                val newsPhoto = v!!.findViewById(R.id.newsPhoto) as ImageView
                val newsTitle = v.findViewById(R.id.newsTitle) as TextView
                val newsName = v.findViewById(R.id.newsName) as TextView

                Glide.with(newsPhoto).load(item.urlToImage).into(newsPhoto)
                newsTitle.text = item.title
                newsName.text = item.source.name
            }
        }
    }


}