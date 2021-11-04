package com.inwooshin.file_database

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inwooshin.file_database.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    //아래 코드로 할때는 버튼이 동작을 안했음 ㄷㄷㄷ
    //val binding by lazy {ActivityMainBinding.inflate(layoutflater)}

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private var transaction : FragmentTransaction? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvMain.setOnNavigationItemSelectedListener(this)

        transaction = supportFragmentManager.beginTransaction()
        transaction!!.add(R.id.fl_container, authority())
        transaction!!.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //TODO("Not yet implemented")
        transaction = supportFragmentManager.beginTransaction()
        when(item.itemId){
            R.id.first -> {
                transaction!!.replace(R.id.fl_container, authority())
                Log.d("안녕", "안녕")
            }
            R.id.second -> {
                transaction!!.replace(R.id.fl_container, fileAndData())
            }
        }
        transaction!!.addToBackStack(null)
        transaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction!!.commit()

        return true
    }


}