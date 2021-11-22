package com.example.papagostduy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.papagostduy.data.PapagoMessage
import com.example.papagostduy.viewmodel.TranslateViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        //translateText()

    }

    fun translateText() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val PapagoService = retrofit.create(NaverPapagoService::class.java)

        val PapagoMethod = PapagoService.getTranslatePapago("tA_4p6FooduqVlTzZFdh","7Ic_JvHndt","ko","en","안녕하세요.");
            PapagoMethod.enqueue(object: Callback<PapagoMessage> {
                override fun onResponse(
                    call: Call<PapagoMessage>,
                    response: Response<PapagoMessage>
                ) {
                    if(response.isSuccessful() && response.body()!= null)
                        Log.d("MainActivity","성공: ${response.raw()} + ${response.body()}")
                    ShowTranslated()
                }

                override fun onFailure(call: Call<PapagoMessage>, t: Throwable) {
                    Toast.makeText(baseContext,"서버에서 데이터를 가져올 수 없습니다",
                        Toast.LENGTH_LONG).show()
                }
            })
    }

    fun ShowTranslated(){
        Log.d("MainActivity","Hello");
    }
}