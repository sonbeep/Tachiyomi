package com.example.tachiyomi.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tachiyomi.R
import com.example.tachiyomi.adapter.TopMovieAdapter
import com.example.tachiyomi.adapter.TopToDetail
import com.example.tachiyomi.databinding.FrgTuTruyenBinding
import com.example.tachiyomi.viewmodel.DetailMovieVM
import com.example.tachiyomi.viewmodel.TrangChuVM
import org.koin.android.ext.android.inject

class FragmentXepHang: Fragment(), TopToDetail {
    private var binding: FrgTuTruyenBinding? = null
    val viewModel: TrangChuVM by inject<TrangChuVM>()
    val viewModelDetail: DetailMovieVM by inject<DetailMovieVM>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgTuTruyenBinding.inflate(inflater, container, false)
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

        }
    }

    private fun listenLiveData() {
        viewModel.allMovieTopRate.observe(viewLifecycleOwner){
            binding?.rycTop?.adapter = TopMovieAdapter(requireContext(), viewModel.allMovieTopRate.value?.allMovie ?: listOf(), this)
        }
    }

    override fun fromTopToDetail(movieId: Int) {
        viewModelDetail.movieId.value = movieId
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.ln_main, FragmentChiTietPhim()).addToBackStack("").commit()
    }
}