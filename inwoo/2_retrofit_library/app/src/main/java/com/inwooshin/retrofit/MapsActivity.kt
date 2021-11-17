package com.inwooshin.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.inwooshin.retrofit.data.Library
import com.inwooshin.retrofit.databinding.ActivityMapsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        loadLibraries()

        // Add a marker in Sydney and move the camera
        // val sydney = LatLng(-34.0, 151.0)
        // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun loadLibraries(){
        //Gson은 JSON의 자바 오브젝트의 직렬화, 역직렬화를 해주는 오픈 소스 자바 라이브러리이다.
        // 전체 클래스 계층에 대한 변환 로직을 한 곳에모으려고할 때 Converter Factory를 사용
        val retrofit = Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN) //여기에서 null 인지 확인하고 http Url 로 변경시키고 넘겨준다.
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SeoulOpenSevice::class.java)

        service.getLibrary(SeoulOpenApi.API_KEY, 200)
            .enqueue(object : Callback<Library>{
                //인터페이스가 콜백이라는 형태로 만들어져있는 것이고
                //인터넷을 통해서 자료를 요청하면 성공시 onResponse 실패시 onFailure 호출
                override fun onResponse(call: Call<Library>, response: Response<Library>) {
                    //body 를 보면 response 라이브러리로 넘어오게 된다.
                    val result = response.body() as Library
                    showLibraries(result)
                }

                override fun onFailure(call: Call<Library>, t: Throwable) {
                    Toast.makeText(this@MapsActivity, "데어터를 가져올 수 없어용", Toast.LENGTH_SHORT).show()
                    Log.e("error", "error=${t.localizedMessage}")
                }
            })
    }

    private fun showLibraries(result: Library?) {
        result?.let {
            //LatLngBounds 클래스는 남서쪽과 북동쪽의 위/경도 좌표가 설정돼 있는 직사각형의
            //지리적 영역을 정의한다.
            val latLngBounds = LatLngBounds.Builder()
            for(lib in it.SeoulPublicLibraryInfo.row){
                //LatLng 이 무엇??
                val position = LatLng(lib.XCNTS.toDouble(), lib.YDNTS.toDouble())
                val marker = MarkerOptions().position(position).title(lib.LBRRY_NAME)
                mMap.addMarker(marker)

                //아래껄 안써서 계속 include 를 못했던 추억이 있다.
                latLngBounds.include(marker.position)
            }

            //마커가 표시된 모든 영역을 보고 싶을때 바운드를 이동시켜야되나
            //아래의 것을 하면 된다. 아래는 마커들의 중앙에 카메라 위치시키고 마커들
            //다 보일 수 있도록 해준다.
            //구글 맵에 사용하기 편한 옵션들이 있다. 그 중 하나가 let long bound

            val bounds = latLngBounds.build()
            val padding = 0

            val camera = CameraUpdateFactory.newLatLngBounds(bounds, padding)
            mMap.moveCamera(camera)
        }
    }
}