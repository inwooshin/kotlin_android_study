package com.example.project1

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.project1.databinding.FragmentABinding
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
// private const val ARG_PARAM1 = "param1"
// private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentA.newInstance] factory method to
 * create an instance of this fragment.
 */

class FragmentA : Fragment() {

    private lateinit var layout: View
    private lateinit var mContext: Context
    private lateinit var button: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_a,container,false)
        layout = view.findViewById(R.id.fragment_a)
        button = view.findViewById(R.id.button)
        button.setOnClickListener { checkPermission() }

        return view
    }


    fun checkPermission(){
        // requireContext() , getContext()
        val r_filePemrmission = ContextCopat.checkSelfPermission(mContext as Activity, Manifest.permission.READ_EXTERNAL_STORAGE)
        //w_filePermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(r_filePermission == PackageManager.PERMISSION_GRANTED) {
            startProcess()
        } else {
            requestPermission()
        }
    }

    fun startProcess() {
        Snackbar.make(layout, R.string.read_permission_snackbar, Snackbar.LENGTH_SHORT).show()
    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(mContext as Activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 99)
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when (requestCode) {
            99 -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startProcess()
                } else {
                    //finish()
                }
            }
        }
    }
}