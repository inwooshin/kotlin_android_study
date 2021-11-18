package kr.co.gnk.SeoulPublicLibraries

import kr.co.gnk.SeoulPublicLibraries.data.Library
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class SeoulOpenApi {
    companion object {
        val DOMAIN = "http://openAPI.seoul.go.kr:8088/"
        val API_KEY = "6b66434577726e7337316251656553"
    }
}

interface SeoulOpenService {
    @GET("{api_key}/json/SeoulPublicLibraryInfo/1/190")
    fun getLibrary(@Path("api_key") key: String): Call<Library>
}