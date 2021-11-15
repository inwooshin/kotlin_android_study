package com.inwooshin.file_database

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.inwooshin.file_database.databinding.FragmentAuthorityBinding
import com.inwooshin.file_database.databinding.FragmentSqliteBinding

class sqlite : Fragment() {

    lateinit var binding : FragmentSqliteBinding
    private var count = 1
    val DB_NAME = "sqlite.sql"
    val DB_VERSION = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSqliteBinding.inflate(inflater, container, false)

        with(binding) {

            val helper = SqliteHelper(requireContext(), DB_NAME, DB_VERSION)
            val adapter = RecyclerAdapter()
            adapter.helper = helper

            val memos = helper.selectMemo()
            adapter.listData.addAll(memos)

            recyclerMemo.adapter = adapter
            recyclerMemo.layoutManager = LinearLayoutManager(requireContext())

            buttonSave.setOnClickListener{
                val content = editMemo.text.toString()
                Log.d("메모", "content = $content")
                if(content.isNotEmpty()){
                    val memo = Memo(null, content, System.currentTimeMillis())
                    helper.insertMemo(memo)
                    //기존 작성글 삭제
                    editMemo.setText("")

                    //목록 갱신
                    adapter.listData.clear()
                    adapter.listData.addAll(helper.selectMemo())
                    adapter.notifyDataSetChanged()
                }
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}