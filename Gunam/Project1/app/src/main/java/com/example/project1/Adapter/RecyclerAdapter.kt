package com.example.project1.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Util.Memo
import com.example.project1.Util.SqliteHelper
import com.example.project1.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var listData = mutableListOf<Memo>()
    var helper: SqliteHelper? = null

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
        var mMemo: Memo? = null

        init {
            binding.buttonDelete.setOnClickListener {
                // deleteMemo() 는 Null 을 허용하지 않음
                // 하지만 mMemo 는 Nullable 이기 때문에 !!를 통해서 Null 강제
                helper?.deleteMemo(mMemo!!)
                listData.remove(mMemo)
                notifyDataSetChanged()
            }
        }
        fun setMemo(memo: Memo) {

            mMemo = memo
            // ItemRecylcerBinding 했으니까 item_recycler.xml 가서
            // View들 Id 확인 하기
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content

            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDateTime.text = "${sdf.format(memo.datetime)}"
        }
    }
}