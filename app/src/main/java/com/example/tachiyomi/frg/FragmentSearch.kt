package com.example.tachiyomi.frg

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
        viewModel.searchResult.observe(viewLifecycleOwner){
            binding?.rycSearch?.adapter = SearchAdapter(requireContext(), viewModel.searchResult.value?.allMovie ?: listOf(), this)
        }

    }

    private fun listenAppEvent() {
        binding?.let { binding->
            binding.btnSearch.setOnClickListener {
                val inputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)

            }
            binding.edtSearch.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.getSearch(binding.edtSearch.text.toString())
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
        }
    }

    override fun fromSearchToDetail(movieId: Int) {
        viewModelDetail.movieId.value = movieId
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.ln_main, FragmentChiTietPhim()).addToBackStack("").commit()

    }
}