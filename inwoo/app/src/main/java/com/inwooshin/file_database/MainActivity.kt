package com.inwooshin.file_database

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.inwooshin.file_database.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //아래 코드로 할때는 버튼이 동작을 안했음 ㄷㄷㄷ
    //val binding by lazy {ActivityMainBinding.inflate(layoutflater)}

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            checkPermission()
            Log.v("check", "버튼 클릭")
        }
    }

    private fun checkPermission(){
        //카메라의 권한 승인상태 가져오기
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        //저장된 값이 승인되었는지 아닌지 확인
        if(cameraPermission == PackageManager.PERMISSION_GRANTED){
            //승인 시 프로그램
            startProcess()
        }
        else{
            //미승인 이면 권한요청
            requestPermission()
        }
    }

    private fun startProcess() {
        Toast.makeText(this, "카메라를 실행합니다.", Toast.LENGTH_SHORT).show()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 99)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            99 ->{
                //권한 결괏값을 확인 후 실행 내용을 결정합니다.
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startProcess()
                }
                else {
                    finish()
                }
            }
        }
    }



}