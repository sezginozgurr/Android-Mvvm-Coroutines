package com.example.androidmvvmcoroutines.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmvvmcoroutines.data.api.ApiService
import com.example.androidmvvmcoroutines.data.api.RetrofitClient
import com.example.androidmvvmcoroutines.data.response.NewsResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call

class HomeViewModel : ViewModel() {
    private val _request: MutableLiveData<NewsResponse> = MutableLiveData()
    val request: LiveData<NewsResponse> = _request
    var loading = MutableLiveData<Boolean>()

    val api: ApiService = RetrofitClient.getRetrofit().create(ApiService::class.java)
    //val newsResponse: Call<NewsResponse> = api.getNews()
    init {
            viewModelScope.launch {
                _request.value = api.getNews()
            }
    }




    /*newsResponse.enqueue(object : Callback<NewsResponse> {
        override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
            response.body().let {
                newsList = it
                setAdapter()
            }
        }

        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {}

    })*/

}