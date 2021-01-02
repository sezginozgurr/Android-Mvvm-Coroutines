package com.example.androidmvvmcoroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.androidmvvmcoroutines.R
import com.example.androidmvvmcoroutines.adapter.BaseAdapter
import com.example.androidmvvmcoroutines.data.network.ApiService
import com.example.androidmvvmcoroutines.data.network.RetrofitClient
import com.example.androidmvvmcoroutines.databinding.ActivityMainBinding
import com.example.androidmvvmcoroutines.model.Article
import com.example.androidmvvmcoroutines.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var newsList: NewsResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api: ApiService = RetrofitClient.getRetrofit().create(ApiService::class.java)

        val newsResponse: Call<NewsResponse> = api.getNews()

        newsResponse.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                response.body().let {
                    newsList = it
                    setAdapter()
                }
            }
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {}

        })

    }

    private fun setAdapter() {
        binding.recyclerNews.apply {
            adapter = BaseAdapter(
                this@MainActivity,
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