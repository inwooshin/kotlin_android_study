package com.hany.retrofittest

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// API interface를 호출할 object
object NaverRepository {

    private val api: Api //인터페이스 구현

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getSearchNews(query: String,
                      onSuccess: (news: List<Items>) -> Unit,
                      onError: () -> Unit ) {
        api.getSearchNews(query = query, display=100)
            .enqueue(object : Callback<ResultGetSearchNews> {
                override fun onResponse(
                    call: Call<ResultGetSearchNews>,
                    response: Response<ResultGetSearchNews>
                ) {
                    if (response.isSuccessful) {
                        //성공시
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.items)
                            //invoke란 이름없 간편하게 호출되는될수 있는 함수 여기서 invoke를 지워도 정상 작동
                            //리스트안에 100개의 items가 있다 이 items는 내가만든 dataclass의 Item 형식
                            Log.d("result","${responseBody?.items.map{it.title}}")
                            // 여기서 map을 통해 100개의 items에서 title만 map으로 빼서 리스트로 만듬
                            //onSuccess시 이를 넘겨줘도 되지만 onSearchNewsFetched에서 파라터를 List<Items> 로해서 안됨 바꾸면도미
                        } else {
                            onError.invoke()
                            Log.d("result","x1")

                        }
                    } else {
                        onError.invoke()
                        Log.d("result","x2")

                    }
                }

                override fun onFailure(call: Call<ResultGetSearchNews>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}