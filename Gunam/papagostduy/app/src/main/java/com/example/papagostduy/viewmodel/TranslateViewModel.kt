package com.example.papagostduy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.papagostduy.NaverAPI
import com.example.papagostduy.NaverPapagoService
import com.example.papagostduy.data.PapagoMessage
import com.example.papagostduy.repository.TranslateRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TranslateViewModel : ViewModel() {

    private val CLIENT_ID = "tA_4p6FooduqVlTzZFdh"
    private val CLIENT_SECRET = "7Ic_JvHndt"

    val sourceText = MutableLiveData<String>()

    private val _targetText = MutableLiveData<String>()
    val targetText: LiveData<String>
        get() = _targetText

    init {
        Log.i("TranslateViewModel", "TranslateViewModel created!")
        sourceText.value = ""
        _targetText.value = ""
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TranslateViewModel", "TranslateViewModel destroyed!")
    }


    fun getTranslatedText(text :String) {
        //  TODO:: 번역하는 API 함수 사용하기
        // "?." : safe call
        sourceText.value = text
        val retrofit: Retrofit = NaverAPI.getInstance()
        val papagoService = retrofit.create(NaverPapagoService::class.java)

        papagoService.getTranslatePapago(CLIENT_ID,CLIENT_SECRET,"ko","en",sourceText.value!!)
                .enqueue(object : Callback<PapagoMessage> {
                    override fun onResponse(call: Call<PapagoMessage>, response: Response<PapagoMessage>) {
                        Log.i("TranslateViewModel","Translated: ${response.body()!!.message.result.translatedText}")
                        Log.i("TranslateViewModel","Translated: ${response.message()}")
                        Log.i("TranslateViewModel","Translated: ${response.body()}")
                        _targetText.value = response.body()!!.message.result.translatedText
                    }

                    override fun onFailure(call: Call<PapagoMessage>, t: Throwable) {
                        Log.e("TranslateViewModel","Retrofit Connect Error")
                    }
                })
    }
}