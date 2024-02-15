package com.example.tachiyomi.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tachiyomi.R
import com.example.tachiyomi.adapter.DaHoanThanhAdapter
import com.example.tachiyomi.databinding.FrgDaHoanThanhBinding
import com.example.tachiyomi.viewmodel.DaHoanThanhVM
import org.koin.android.ext.android.inject

class FragmentDaHoanThanh : Fragment() {
    private var binding : FrgDaHoanThanhBinding? = null
    val viewModel : DaHoanThanhVM by inject<DaHoanThanhVM>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgDaHoanThanhBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
        listenAppEvent()
        listenLiveData()

    }

    private fun setUpUI() {
        viewModel.getAllMovieFavorite()
    }

    private fun listenAppEvent() {
        binding?.let { binding ->
            binding.btnBack.setOnClickListener {
                binding.btnBack.startAnimation(AnimationUtils.loadAnimation(requireContext(), androidx.appcompat.R.anim.abc_fade_in))
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.ln_main, FragmentTrangChu()).commit()
            }
            binding.btnThieuNhi.setOnClickListener {
                viewModel.topic.value = 1
            }
            binding.btnKhac.setOnClickListener {
                viewModel.topic.value = 0
            }
        }
    }

    private fun listenLiveData() {

        viewModel.topic.observe(viewLifecycleOwner) {
            if (it == 1) {
                binding?.btnThieuNhi?.background = resources.getDrawable(R.drawable.bg_hoan_thanh_vang)
                binding?.btnKhac?.background = resources.getDrawable(R.drawable.bg_hoan_thanh_xam)
                binding?.btnThieuNhi?.setTextColor(resources.getColor(R.color.yellow1))
                binding?.btnKhac?.setTextColor(resources.getColor(R.color.khac))
            } else if (it == 0) {
                binding?.btnThieuNhi?.background = resources.getDrawable(R.drawable.bg_hoan_thanh_xam)
                binding?.btnKhac?.background = resources.getDrawable(R.drawable.bg_hoan_thanh_vang)
                binding?.btnThieuNhi?.setTextColor(resources.getColor(R.color.khac))
                binding?.btnKhac?.setTextColor(resources.getColor(R.color.yellow1))
            }
        }
        viewModel.allMovie.observe(viewLifecycleOwner){
            binding?.rycDaHoanThanh?.adapter = DaHoanThanhAdapter(requireContext(), viewModel.allMovie.value?.allMovie ?: listOf())
        }

    }
}