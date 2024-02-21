package com.example.tachiyomi.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tachiyomi.databinding.FrgTrailerBinding
import com.example.tachiyomi.viewmodel.DetailMovieVM
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class FragmentTrailer : Fragment() {
    var binding: FrgTrailerBinding? = null
    val viewModel: DetailMovieVM by inject<DetailMovieVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgTrailerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        listenAppEvent()
        listentLiveData()
    }

    private fun setUpUI() {
        viewModel.getDetailMovie(viewModel.movieId.value ?: 0)
        binding?.wvTrailer?.settings?.javaScriptEnabled = true
        viewModel.detailMovie.observe(viewLifecycleOwner){
            for (list in viewModel.detailMovie.value?.videos?.trailer ?: listOf()){
                if (list.type == "Trailer"){
                    binding?.wvTrailer?.loadUrl("https://www.youtube.com/watch?v=${list.key}")
                }
            }
        }

        binding?.key?.setOnClickListener {
            viewModel.detailMovie.observe(viewLifecycleOwner){
                for (list in viewModel.detailMovie.value?.videos?.trailer ?: listOf()){
                    if (list.type == "Trailer"){
                        viewModel.keyVideo.value = list.key
                        binding?.wvTrailer?.loadUrl("https://www.youtube.com/watch?v=${list.key}")
                    }
                }
            }
        }

    }

    private fun listenAppEvent() {

    }

    private fun listentLiveData() {

    }
}