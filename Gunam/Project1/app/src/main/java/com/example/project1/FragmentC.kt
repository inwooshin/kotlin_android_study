package com.example.project1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.project1.Adapter.RoomAdapter
import com.example.project1.Model.RoomMemo
import com.example.project1.Util.RoomHelper
import com.example.project1.Util.RoomMemoDao
import com.example.project1.databinding.FragmentCBinding

class FragmentC : Fragment() {

    lateinit var binding : FragmentCBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_c, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var helper: RoomHelper? = null
        helper = Room.databaseBuilder(requireContext(), RoomHelper::class.java, "room_memo")
            .allowMainThreadQueries()
            .build()

        val adapter = RoomAdapter()
        adapter.helper = helper
        // helper 가 Null 일수 있으니까 ?.
        // helper 가 Null 이면 roomMemoDao()도 Null 일수 있으니 ?.
        // ?: (Elvis Operator)를 활용해 앞의 2개가 null 일 경우 사용할 default 값 등록
        adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?: listOf())
        binding.recyclerMemo2.adapter = adapter
        binding.recyclerMemo2.layoutManager = LinearLayoutManager(requireContext())

        binding.buttonSave2.setOnClickListener {
            if(binding.editMemo2.text.toString().isNotEmpty()) {
                val memo = RoomMemo(binding.editMemo2.text.toString(),System.currentTimeMillis())
                helper?.roomMemoDao()?.insert(memo)

                adapter.listData.clear()
                adapter.listData.addAll(helper.roomMemoDao()?.getAll()?: listOf())

                adapter.notifyDataSetChanged()
                binding.editMemo2.setText("")
            }
        }
    }
}