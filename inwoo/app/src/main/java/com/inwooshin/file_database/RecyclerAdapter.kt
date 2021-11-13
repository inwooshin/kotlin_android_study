package com.inwooshin.file_database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inwooshin.file_database.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter  : RecyclerView.Adapter<RecyclerAdapter.Holder>(){
    var listData = mutableListOf<Memo>()
    var helper : SqliteHelper? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    //실제 화면에 그려주는 함수
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    inner class Holder(val binding : ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root){
        var mMemo: Memo? = null
        init{
            binding.buttonDelete.setOnClickListener{
                helper?.deleteMemo(mMemo!!)
                listData.remove(mMemo)
                notifyDataSetChanged()
            }
        }
        fun setMemo(memo:Memo){
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            val datetime = sdf.format(memo.datetime)
            //long 타입을 날짜포맷으로 바꿔주는 것이 있다고 함
            binding.textDatetime.text = "$datetime"

            this.mMemo = memo
        }
    }
}