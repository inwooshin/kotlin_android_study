package com.inwooshin.retrofit

import com.inwooshin.retrofit.data.Library
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class SeoulOpenApi {
    //companion object 가 뭐지?
    companion object {
        val DOMAIN = "http://openapi.seoul.go.kr:8088/"
        val API_KEY = "5861715074646b7236315256437a63"
    }
}

interface SeoulOpenSevice {
    @GET("{api_key}/json/SeoulPublicLibraryInfo/1/{end}/")

    //key 값이 retrofit 이 호출해주는 주소의 api key 라는 이름으로 사용된다.
    fun getLibrary(@Path("api_key") key:String, @Path("end") limit:Int) : Call<Library>
}