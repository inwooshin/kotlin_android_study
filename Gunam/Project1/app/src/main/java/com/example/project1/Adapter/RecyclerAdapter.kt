package com.example.project1.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Util.Memo
import com.example.project1.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var listData = mutableListOf<Memo>()

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

    class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        fun setMemo(memo: Memo) {
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content

            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDateTime.text = "${sdf.format(memo.datetime)}"
        }
    }
}