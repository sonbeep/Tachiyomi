package com.example.tachiyomi.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tachiyomi.R
import com.example.tachiyomi.adapter.BannerAdapter
import com.example.tachiyomi.adapter.TruyenAdapter
import com.example.tachiyomi.databinding.FrgTrangChuBinding
import com.example.tachiyomi.model.Banner
import com.example.tachiyomi.viewmodel.DaHoanThanhVM
import com.example.tachiyomi.viewmodel.TrangChuVM
import org.koin.android.ext.android.inject

class FragmentTrangChu : Fragment() {
    var binding: FrgTrangChuBinding? = null
    private val listBanner =
        arrayListOf<Banner>(Banner(R.drawable.banner), Banner(R.drawable.banner))
    val viewModel : TrangChuVM by inject<TrangChuVM> ()
    val hoanThanhVM: DaHoanThanhVM by inject<DaHoanThanhVM>()


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

        setUpUI()
        listenAppEvent()
        listenLiveData()
    }

    private fun setUpUI() {

        hoanThanhVM.getAllMovieFavorite()
        viewModel.getAllMoviePopular()
        viewModel.getAllMovieTopRate()
        viewModel.getAllMovieUpComing()
    }

    private fun listenLiveData() {

        viewModel.topic.observe(viewLifecycleOwner) {
            if (it == 1) {
                binding?.btnThieuNhi?.background = resources.getDrawable(R.drawable.bg_chu_de_vang)
                binding?.btnKhac?.background = resources.getDrawable(R.drawable.bg_chu_de_xam)
                binding?.btnThieuNhi?.setTextColor(resources.getColor(R.color.bg_topic))
                binding?.btnKhac?.setTextColor(resources.getColor(R.color.khac))
            } else if (it == 0) {
                binding?.btnThieuNhi?.background = resources.getDrawable(R.drawable.bg_chu_de_xam)
                binding?.btnKhac?.background = resources.getDrawable(R.drawable.bg_chu_de_vang)

                binding?.btnThieuNhi?.setTextColor(resources.getColor(R.color.khac))
                binding?.btnKhac?.setTextColor(resources.getColor(R.color.bg_topic))
            }
        }
        hoanThanhVM.allMovie.observe(viewLifecycleOwner){
            binding?.rycFavorite?.adapter = TruyenAdapter(requireContext(), hoanThanhVM.allMovie.value?.allMovie ?: listOf())
            binding?.viewPagerBanner?.adapter = BannerAdapter(requireContext(),ArrayList(hoanThanhVM.allMovie.value?.allMovie) ?: arrayListOf())
        }
        viewModel.allMoviePopular.observe(viewLifecycleOwner){
            binding?.rycPopular?.adapter = TruyenAdapter(requireContext(), viewModel.allMoviePopular.value?.allMovie ?: listOf())
        }
        viewModel.allMovieUpComing.observe(viewLifecycleOwner){
            binding?.rycUpComing?.adapter = TruyenAdapter(requireContext(), viewModel.allMovieUpComing.value?.allMovie ?: listOf())
        }
        viewModel.allMovieTopRate.observe(viewLifecycleOwner){
            binding?.rycTopRate?.adapter = TruyenAdapter(requireContext(), viewModel.allMovieTopRate.value?.allMovie ?: listOf())
        }


    }

    private fun listenAppEvent() {
        binding?.let { binding ->

            binding.btnThieuNhi.setOnClickListener {
                viewModel.topic.value = 1
            }
            binding.btnKhac.setOnClickListener {
                viewModel.topic.value = 0
            }
            binding.btnDaHoanThanh.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.ln_main, FragmentDaHoanThanh()).commit()
            }
        }


    }
}

