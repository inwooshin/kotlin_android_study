package com.example.papagostduy

import com.example.papagostduy.data.PapagoMessage
import retrofit2.Call
import retrofit2.http.*

public interface NaverPapagoAPI {
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

