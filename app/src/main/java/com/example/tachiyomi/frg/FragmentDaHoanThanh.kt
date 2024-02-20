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
import com.example.tachiyomi.adapter.OnGoToDetail
import com.example.tachiyomi.databinding.FrgDaHoanThanhBinding
import com.example.tachiyomi.viewmodel.DaHoanThanhVM
import com.example.tachiyomi.viewmodel.DetailMovieVM
import com.example.tachiyomi.viewmodel.TrangChuVM
import org.koin.android.ext.android.inject

class FragmentDaHoanThanh : Fragment(), OnGoToDetail {
    private var binding : FrgDaHoanThanhBinding? = null
    val viewModel : DaHoanThanhVM by inject<DaHoanThanhVM>()
    val viewModelDetail : DetailMovieVM by inject<DetailMovieVM>()
    val trangChuVM : TrangChuVM by inject<TrangChuVM>()

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

    }

    private fun listenAppEvent() {
        binding?.let { binding ->
            binding.btnBack.setOnClickListener {
                binding.btnBack.startAnimation(AnimationUtils.loadAnimation(requireContext(), androidx.appcompat.R.anim.abc_fade_in))
                fragmentManager?.popBackStack()
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
        viewModel.muc.observe(viewLifecycleOwner){ muc ->
            if (muc == 2){
                viewModel.getAllMovieFavorite()
                viewModel.allMovie.observe(viewLifecycleOwner){
                    binding?.rycDaHoanThanh?.adapter = DaHoanThanhAdapter(requireContext(), viewModel.allMovie.value?.allMovie ?: listOf(), this)
                }
            }else if (muc == 1){
                trangChuVM.getAllMoviePopular()
                trangChuVM.allMoviePopular.observe(viewLifecycleOwner){
                    binding?.rycDaHoanThanh?.adapter = DaHoanThanhAdapter(requireContext(), trangChuVM.allMoviePopular.value?.allMovie ?: listOf(), this)
                }
            }else if (muc == 3){
                trangChuVM.getAllMovieTopRate()
                trangChuVM.allMovieTopRate.observe(viewLifecycleOwner){
                    binding?.rycDaHoanThanh?.adapter = DaHoanThanhAdapter(requireContext(), trangChuVM.allMovieTopRate.value?.allMovie ?: listOf(), this)
                }

            }else if (muc == 4){
                trangChuVM.getAllMovieUpComing()
                trangChuVM.allMovieUpComing.observe(viewLifecycleOwner){
                    binding?.rycDaHoanThanh?.adapter = DaHoanThanhAdapter(requireContext(), trangChuVM.allMovieUpComing.value?.allMovie ?: listOf(), this)

                }
            }
        }
    }

    override fun onClickDetail(movieId: Int) {
        viewModelDetail.movieId.value = movieId
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.ln_main, FragmentChiTietPhim()).addToBackStack("").commit()
    }
}