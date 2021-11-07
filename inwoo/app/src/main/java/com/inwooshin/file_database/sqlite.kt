package com.inwooshin.file_database

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.inwooshin.file_database.databinding.FragmentSqliteBinding

class sqlite : Fragment() {

    lateinit var binding : FragmentSqliteBinding
    private var count = 1
    val DB_NAME = "sqlite.sql"
    val DB_VERSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSqliteBinding.inflate(inflater, container, false)

        with(binding) {


            val helper = SqliteHelper(requireContext(), DB_NAME, DB_VERSION)
            val memo = Memo(1, "내용", 12345)
            //helper.insertMemo(memo) 요런식으로 사용
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}