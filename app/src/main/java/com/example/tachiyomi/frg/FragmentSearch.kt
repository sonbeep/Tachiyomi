package com.example.tachiyomi.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tachiyomi.R
import com.example.tachiyomi.adapter.SearchAdapter
import com.example.tachiyomi.adapter.SearchToDetail
import com.example.tachiyomi.databinding.FrgSearchBinding
import com.example.tachiyomi.viewmodel.DetailMovieVM
import com.example.tachiyomi.viewmodel.SearchVM
import org.koin.android.ext.android.inject

class FragmentSearch: Fragment(), SearchToDetail {
    var binding: FrgSearchBinding? = null
    val viewModel : SearchVM by inject<SearchVM>()
    val viewModelDetail: DetailMovieVM by inject<DetailMovieVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgSearchBinding.inflate(inflater, container, false)
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

    private fun listenLiveData() {


    }

    private fun listenAppEvent() {
        binding?.let { binding->
            binding.btnSearch.setOnClickListener {
                viewModel.getSearch(binding.edtSearch.text.toString())
                viewModel.searchResult.observe(viewLifecycleOwner){
                    binding?.rycSearch?.adapter = SearchAdapter(requireContext(), viewModel.searchResult.value?.allMovie ?: listOf(), this)
                }
            }
        }
    }

    override fun fromSearchToDetail(movieId: Int) {
        viewModelDetail.movieId.value = movieId
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.ln_main, FragmentChiTietPhim()).addToBackStack("").commit()

    }
}