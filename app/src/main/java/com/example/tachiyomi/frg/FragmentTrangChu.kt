package com.example.tachiyomi.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tachiyomi.R
import com.example.tachiyomi.databinding.FrgTrangChuBinding
import com.example.tachiyomi.viewmodel.TrangChuVM

class FragmentTrangChu : Fragment() {
     var binding : FrgTrangChuBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgTrangChuBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(TrangChuVM::class.java)

        binding?.btnThieuNhi?.setOnClickListener {
            viewModel.topic.value = 1
            Toast.makeText(requireContext(), "1", Toast.LENGTH_SHORT).show()
        }
        binding?.btnKhac?.setOnClickListener {
            viewModel.topic.value = 0
            Toast.makeText(requireContext(), "0", Toast.LENGTH_SHORT).show()
        }


        viewModel.topic.observe(viewLifecycleOwner){
            if (it == 1){
                binding?.btnThieuNhi?.background = resources.getDrawable(R.drawable.bg_chu_de_vang)
                binding?.btnKhac?.background = resources.getDrawable(R.drawable.bg_chu_de_xam)
                binding?.btnThieuNhi?.setTextColor(resources.getColor(R.color.bg_topic))
                binding?.btnKhac?.setTextColor(resources.getColor(R.color.khac))
            }else if (it == 0){
                binding?.btnThieuNhi?.background = resources.getDrawable(R.drawable.bg_chu_de_xam)
                binding?.btnKhac?.background = resources.getDrawable(R.drawable.bg_chu_de_vang)

                binding?.btnThieuNhi?.setTextColor(resources.getColor(R.color.khac))
                binding?.btnKhac?.setTextColor(resources.getColor(R.color.bg_topic))
            }
        }
    }


}

