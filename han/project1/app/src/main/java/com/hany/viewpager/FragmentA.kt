package com.hany.viewpager

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentResultListener
import com.hany.viewpager.databinding.FragmentABinding

class FragmentA : Fragment() {




    lateinit var binding:FragmentABinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentABinding.inflate(inflater, container,false)
        binding.btnCamera.setOnClickListener{
            checkPermission()
        }
        return binding.root
    }

    fun checkPermission(){
        val cameraPermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED){
            startProcess()
        }else{
            requestPermission()
        }
    }
    // 프레그먼트는 별도의 context를 가지지 않기 때문에 requireActivity() 사용
    // 프레그먼트

    fun startProcess(){
        Toast.makeText(requireActivity(),"카메라를 실행합니다", Toast.LENGTH_LONG).show()
    }
    fun requestPermission(){
        ActivityCompat.requestPermissions(requireActivity(),arrayOf(Manifest.permission.CAMERA),99)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions:Array<out String>,
        grantResults:IntArray
    ){
      when (requestCode){
          99 -> {
              if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                  startProcess()
              }
          }

      }
    }
}