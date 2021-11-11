package com.inwooshin.file_database

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.inwooshin.file_database.databinding.FragmentAuthorityBinding

class authority : Fragment() {
    lateinit var mainActivity : MainActivity
    lateinit var binding: FragmentAuthorityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAuthorityBinding.inflate(inflater, container, false)

        //아래의 코드와 같다.
        //inflater.inflate(R.layout.fragment_authority, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            checkPermission()
            Log.v("check", "버튼 클릭")
        }
    }

    private fun checkPermission(){
        //카메라의 권한 승인상태 가져오기 여기서는 class를 requireActivity() 로 불러왔다.
        //getActivity 를 하는 것보다 더 분명한 exception 을 던진다고 한다.
        val cameraPermission = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA)

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
        Toast.makeText(context, "카메라를 실행합니다.", Toast.LENGTH_SHORT).show()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), 99)

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
                    //finish() 이 바로 안되기 때문에 이렇게 짬
                    activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.remove(this)
                        ?.commit()
                }
            }
        }
    }
}