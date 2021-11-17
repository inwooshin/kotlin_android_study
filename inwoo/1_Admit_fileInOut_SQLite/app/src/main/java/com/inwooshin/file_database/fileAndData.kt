package com.inwooshin.file_database

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inwooshin.file_database.databinding.FragmentFileAndDataBinding
import java.io.*

class fileAndData : Fragment() {
    lateinit var binding : FragmentFileAndDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun readTextFile(path : String ) : String{
        val fullpath = File(requireContext().filesDir.path + '/' + path)
        if(!fullpath.exists()) return ""

        val reader = FileReader(fullpath)
        val buffer = BufferedReader(reader)

        //속도는 빌더가 더 빠른데 버퍼는 동시성을 지원한다. StringBuffer()
        val result = StringBuilder()

        //아래처럼 String? 의 옵션을 줌으로
        //nullable 로 해야한다.
        var temp  : String? = ""

        while(true){
            //조건은 readLine 읽고 더이상 없을 경우!
            temp = buffer.readLine()
            if(temp == null) break
            result.append(temp).append('\n')
        }

        buffer.close()
        reader.close()
        return result.toString()
    }

    private fun writeTextFile(drName: String, fName: String, content: String) {
        //filesDir.path 를 사용하고 싶었는데
        //fragment 내에서는 requireContext로
        //가져와야하나보다.

        //app 기본 경로에서 files dir 밑에
        //memo dir 가 생성된다.
        val dir = File(requireContext().filesDir.path + "/" + drName)

        if(!dir.exists()) dir.mkdirs()
        val filepath = dir.path + "/" + fName
        val writer = FileWriter(filepath)
        val buffer = BufferedWriter(writer)

        Log.d("경로", filepath)

        buffer.write(content)
        buffer.close()
        buffer.close()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val directory_name = "memo"
            val filename = "memo01.txt"

            btnSave.setOnClickListener() {
                val textContext = etEnter.text.toString()
                Log.d("내용", textContext)
                writeTextFile(directory_name, filename, textContext)
            }

            btnRead.setOnClickListener(){
                val path = directory_name + "/" + filename
                val writtenContent = readTextFile(path)
                tvShow.text = "경로 : " + requireContext().filesDir.path + path + "\n\n 내용 : " + writtenContent
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFileAndDataBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }
}