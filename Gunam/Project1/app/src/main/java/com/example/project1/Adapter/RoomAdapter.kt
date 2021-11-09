package com.example.project1.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Model.RoomMemo
import com.example.project1.Util.RoomHelper
import com.example.project1.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RoomAdapter: RecyclerView.Adapter<RoomAdapter.Holder>() {
    var listData = mutableListOf<RoomMemo>()
    var helper: RoomHelper? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        var mRoomMemo: RoomMemo? = null

        init {
            binding.buttonDelete.setOnClickListener {
                helper?.roomMemoDao()?.delete(mRoomMemo!!)
                listData.remove(mRoomMemo)
                notifyDataSetChanged()
            }
        }

        fun setMemo(memo: RoomMemo) {
            mRoomMemo = memo
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content

            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDateTime.text = "${sdf.format(memo.datetime)}"
        }
    }
}