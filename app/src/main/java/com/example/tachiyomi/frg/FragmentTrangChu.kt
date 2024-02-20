package com.example.tachiyomi.frg

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tachiyomi.R
import com.example.tachiyomi.adapter.BannerAdapter
import com.example.tachiyomi.adapter.CallBackGoToDetail
import com.example.tachiyomi.adapter.TruyenAdapter
import com.example.tachiyomi.databinding.FrgTrangChuBinding
import com.example.tachiyomi.model.Banner
import com.example.tachiyomi.viewmodel.DaHoanThanhVM
import com.example.tachiyomi.viewmodel.DetailMovieVM
import com.example.tachiyomi.viewmodel.TrangChuVM
import org.koin.android.ext.android.inject
import java.util.Timer
import java.util.TimerTask

class FragmentTrangChu : Fragment(), CallBackGoToDetail {
    var binding: FrgTrangChuBinding? = null
    val viewModel : TrangChuVM by inject<TrangChuVM> ()
    val hoanThanhVM: DaHoanThanhVM by inject<DaHoanThanhVM>()
    val viewModelDetail: DetailMovieVM by inject<DetailMovieVM>()
    private val handler = Handler()
    private var currentPage = 0
    private val delay: Long = 3000


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

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (currentPage == hoanThanhVM.allMovie.value?.allMovie?.size) {
                        currentPage = 0
                    }
                    binding?.viewPagerBanner?.setCurrentItem(currentPage++, true)
                }
            }
        }, 0, delay)
    }

    private fun listenLiveData() {


        hoanThanhVM.allMovie.observe(viewLifecycleOwner){
            binding?.rycFavorite?.adapter = TruyenAdapter(requireContext(), hoanThanhVM.allMovie.value?.allMovie ?: listOf(),this)
            binding?.viewPagerBanner?.adapter = BannerAdapter(requireContext(),ArrayList(hoanThanhVM.allMovie.value?.allMovie) ?: arrayListOf())
        }
        viewModel.allMoviePopular.observe(viewLifecycleOwner){
            binding?.rycPopular?.adapter = TruyenAdapter(requireContext(), viewModel.allMoviePopular.value?.allMovie ?: listOf(),this)
        }
        viewModel.allMovieUpComing.observe(viewLifecycleOwner){
            binding?.rycUpComing?.adapter = TruyenAdapter(requireContext(), viewModel.allMovieUpComing.value?.allMovie ?: listOf(),this)
        }
        viewModel.allMovieTopRate.observe(viewLifecycleOwner){
            binding?.rycTopRate?.adapter = TruyenAdapter(requireContext(), viewModel.allMovieTopRate.value?.allMovie ?: listOf(),this)
        }


    }

    private fun listenAppEvent() {
        binding?.let { binding ->

            binding.btnDaHoanThanh.setOnClickListener {
                hoanThanhVM.muc.value = 1
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.ln_main, FragmentDaHoanThanh()).addToBackStack("").commit()
            }
            binding.btnMoiNhat.setOnClickListener {
                hoanThanhVM.muc.value = 2
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.ln_main, FragmentDaHoanThanh()).addToBackStack("").commit()
            }
            binding.btnNoiDungVuaXem.setOnClickListener {
                hoanThanhVM.muc.value = 3
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.ln_main, FragmentDaHoanThanh()).addToBackStack("").commit()
            }
            binding.btnTimKiem.setOnClickListener {
                hoanThanhVM.muc.value = 4
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.ln_main, FragmentDaHoanThanh()).addToBackStack("").commit()
            }


        }
    }

    override fun goToDetail(movieId: Int) {
        viewModelDetail.movieId.value = movieId
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.ln_main, FragmentChiTietPhim()).addToBackStack("").commit()
    }
}

