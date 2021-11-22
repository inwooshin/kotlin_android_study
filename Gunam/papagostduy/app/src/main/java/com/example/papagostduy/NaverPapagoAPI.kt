package com.example.papagostduy

import com.example.papagostduy.data.PapagoMessage
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object NaverAPI {

    private val BASE_URL = "https://openapi.naver.com/"
    private var instance : Retrofit? = null

    fun getInstance() : Retrofit {
        if(instance == null ){
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }

}

public interface NaverPapagoService {
    @FormUrlEncoded
    @POST("v1/papago/n2mt")
    fun getTranslatePapago(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Field("source") source: String,
        @Field("target") target: String,
        @Field("text") text : String
    ): Call<PapagoMessage>
}

