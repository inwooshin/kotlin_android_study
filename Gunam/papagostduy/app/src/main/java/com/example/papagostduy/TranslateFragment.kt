package com.example.papagostduy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.papagostduy.databinding.TranslateFragmentBinding
import com.example.papagostduy.viewmodel.TranslateViewModel

class TranslateFragment : Fragment() {
    private val TAG: String = "TranslateFragment"

    private lateinit var binding: TranslateFragmentBinding
    private lateinit var viewModel: TranslateViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TranslateFragmentBinding.inflate(inflater,container,false)

        Log.i(TAG,"Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(TranslateViewModel::class.java)


        binding.btnTranslate.setOnClickListener {
            viewModel.getTranslatedText(binding.editTextSource.text.toString())

        }

        // ViewModel 에서 Text 바꾸면 Fragment 에서도 target text 가 알아서 바뀐다
        // 왜 this -> viewLifeCycleOwner
        viewModel.targetText.observe(viewLifecycleOwner, Observer { newSourceText ->
            binding.textTarget.text = newSourceText
        })


        return binding.root
    }
}