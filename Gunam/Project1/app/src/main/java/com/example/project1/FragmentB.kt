package com.example.project1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1.Adapter.RecyclerAdapter
import com.example.project1.Util.Memo
import com.example.project1.Util.SqliteHelper
import com.example.project1.databinding.FragmentBBinding

class FragmentB : Fragment() {

    // Bindng은 항상 카멜표기법 + Binding
    lateinit var binding : FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment Layout 불러 올 수 있게 Binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_b, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SqliteHelper 다시 한번 복습
        val helper = SqliteHelper(requireContext(),"memo",1)

        val adapter = RecyclerAdapter()
        adapter.helper = helper
        adapter.listData.addAll(helper.selectMemo())

        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(requireContext())

        binding.buttonSave.setOnClickListener {
            if(binding.editMemo.text.toString().isNotEmpty()) {
                val memo = Memo(null, binding.editMemo.text.toString(), System.currentTimeMillis())

                helper.insertMemo(memo)

                // 왜 Clear 해주는 거?
                // 아 sqlite로 부터 Data 불러올거니까 다 저장되어있겠구나
                adapter.listData.clear()

                adapter.listData.addAll(helper.selectMemo())
                adapter.notifyDataSetChanged()

                binding.editMemo.setText("")
            }
        }
    }
}