package com.hany.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager

import com.hany.viewpager.databinding.FragmentBBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentC.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentB : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentBBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBBinding.inflate(inflater, container,false)
        binding.buttonSave.setOnClickListener{
            Log.d("test log", "개발자용 로그")
        }
        val helper =SqliteHelper(requireContext(),"memo",1)
        val adapter =RecyclerAdapter()
        adapter.helper = helper
        adapter.listData.addAll(helper.selectMemo())
        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(requireContext())
        binding.buttonSave.setOnClickListener {
            if(binding.editMemo.text.toString().isNotEmpty()){
                val memo = Memo(null,binding.editMemo.text.toString(),System.currentTimeMillis())
                helper.insertMemo(memo)
                adapter.listData.clear()
                adapter.listData.addAll(helper.selectMemo())
                adapter.notifyDataSetChanged()
                binding.editMemo.setText("")
            }
        }
        return binding.root
    }
}