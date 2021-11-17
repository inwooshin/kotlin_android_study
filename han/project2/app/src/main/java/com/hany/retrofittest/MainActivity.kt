package com.hany.retrofittest

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hany.retrofittest.NaverRepository.getSearchNews
import com.hany.retrofittest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        // 구분선 생
        binding.bSearch.setOnClickListener() {
            var keyword = binding.eSearchWord.text.toString()
            getSearchNews(keyword)
        }
    }


    private fun getSearchNews(searchWord: String) {
        NaverRepository.getSearchNews(
            searchWord,
            ::onSearchNewsFetched,
            ::onError
        )
    }
    private fun onSearchNewsFetched(news: List<Items>) {
// 연결에 성공했을
        val data:MutableList<DataMemo> = loadData(news)
        var adapter = CustomAdapter()

        adapter.listData = data
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


//        binding.tResult.text = news.map{it.title}.toString()
//        Log.d("news",news.toString())

    }

    private fun onError() {
    //연결에 실패헀을떄
        Toast.makeText(this, "error result", Toast.LENGTH_SHORT).show()
    }
    fun loadData(news: List<Items>): MutableList<DataMemo>{
        //데이터 만들기
        val data: MutableList<DataMemo> = mutableListOf()
        for(no in 1..10){
            // 개별 데이터 생성
            val title = news.get(no).title
            val des = news.get(no).description
            // 100 개의 Memo 클래스를 생성
            val day = news.get(no).pubDate
            var memo = DataMemo(title, des, day)
            // 데이터 배열에 담는다
            data.add(memo)
        }

//        val memo = DataMemo(news.map{it.title}.toString(),news.map{it.description}.toString(),news.map{it.pubDate}.toString())
//        data.add(memo)
        return data
    }

}
//참 https://accounts.google.com/_/back?backstep=1
