package com.inwooshin.myapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.inwooshin.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder


//Client ID : QNGLsNsaZaYH0HAbEXWi
//Client PW : VPCltrGKzP

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val CLIENT_ID : String = "QNGLsNsaZaYH0HAbEXWi"
    val CLIENT_SECRET : String = "VPCltrGKzP"
    val BASE_URL_NAVER_API : String = "https://openapi.naver.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            toKoreanButton.setOnClickListener{
                var textInput = input.text.toString()
                if(textInput.trim().length <= 0){
                    Toast.makeText(this@MainActivity,"값을 입력하세요", Toast.LENGTH_SHORT).show()
                }
                else {
                    translateText(textInput, "en", "ko")
                }
            }

            toEnglishButton.setOnClickListener{
                var textInput = input.text.toString()

                if(textInput.trim().length <= 0){
                    Toast.makeText(this@MainActivity,"값을 입력하세요", Toast.LENGTH_SHORT).show()
                }
                else {
                    translateText(textInput, "ko", "en")
                }
            }
        }
    }

    fun translateText(input:String, start : String?, end : String?){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(NaverAPI::class.java)

        val callPostTransferPapago = api.transferPapago(CLIENT_ID, CLIENT_SECRET,
            start!!, end!!, input)

        callPostTransferPapago.enqueue(object : Callback<ResultTransferPapago> {
            override fun onResponse(
                call: Call<ResultTransferPapago>,
                response: Response<ResultTransferPapago>
            ) {
                var result = response.body() as ResultTransferPapago?
                Log.d("success", "성공 : $input ${response.body()} ")
                binding.result.text = result!!.message.result.translatedText
            }

            override fun onFailure(call: Call<ResultTransferPapago>, t: Throwable) {
                Log.d("err", "실패 : $t")
            }
        })
    }


}




