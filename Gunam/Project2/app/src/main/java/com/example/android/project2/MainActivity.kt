package com.example.android.project2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.project2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRequest.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    var urlText = binding.editUrl.text.toString()
                    if(!urlText.startsWith("https")) {
                        urlText = "https://${urlText}"
                    }

                    var url = URL(urlText)
                    val urlConnection = url.openConnection() as HttpURLConnection
                    urlConnection.requestMethod = "GET"

                    if(urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                        val streamReader = InputStreamReader(urlConnection.inputStream)
                        val buffered = BufferedReader(streamReader)

                        val content = StringBuilder()
                        while (true) {
                            val line = buffered.readLine()?: break
                            content.append(line)

                            buffered.close()
                            urlConnection.disconnect()

                            launch(Dispatchers.Main) {
                                binding.textContent.text = content.toString()
                            }

                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
        
        binding.btnRetrofit.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}