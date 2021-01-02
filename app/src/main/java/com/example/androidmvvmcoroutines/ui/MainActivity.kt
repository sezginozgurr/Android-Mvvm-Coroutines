package com.example.androidmvvmcoroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.androidmvvmcoroutines.R
import com.example.androidmvvmcoroutines.adapter.BaseAdapter
import com.example.androidmvvmcoroutines.databinding.ActivityMainBinding
import com.example.androidmvvmcoroutines.model.NewsResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: BaseAdapter<NewsResponse>? = null
    private val newsList: ArrayList<NewsResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerNews.apply {
            adapter = BaseAdapter(
                this@MainActivity,
                R.layout.row_item_news,
                newsList
            ) { v, item, position ->
                val newsPhoto = v!!.findViewById(R.id.newsPhoto) as ImageView
                val newsTitle = v.findViewById(R.id.newsTitle) as TextView
                val newsName = v.findViewById(R.id.newsName) as TextView

                Glide.with(newsPhoto).load(item.articles.urlToImage).into(newsPhoto)
                newsTitle.text = item.articles.title
                newsName.text = item.articles.source.name
            }
        }
    }


}