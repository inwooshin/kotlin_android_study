package com.example.papagostduy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.papagostduy.data.PapagoMessage
import com.example.papagostduy.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val CLIENT_ID = "tA_4p6FooduqVlTzZFdh"
    private val CLIENT_SECRET = "7Ic_JvHndt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        translateText()
    }

    fun translateText() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val PapagoService = retrofit.create(NaverPapagoAPI::class.java)

        val PapagoMethod = PapagoService.getTranslatePapago(CLIENT_ID,CLIENT_SECRET,"ko","en","안녕하세요.");
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