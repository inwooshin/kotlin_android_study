package com.hany.retrofittest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hany.retrofittest.databinding.ItemRecyclerBinding

class CustomAdapter: RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<DataMemo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}
class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root){
    init {
        binding.root.setOnClickListener {
            Toast.makeText(binding.root.context
                ,"클릭된 아이템=${binding.tilteText.text}"
                , Toast.LENGTH_LONG).show()
        }
    }

    fun setMemo(memo:DataMemo) {
        binding.tilteText.text = "${memo.title}"
        binding.descriptText.text = memo.descript
        binding.dateText.text = memo.date
    }
}