package com.example.tachiyomi.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tachiyomi.databinding.FrgChiTietPhimBinding
import com.example.tachiyomi.viewmodel.DaHoanThanhVM
import com.example.tachiyomi.viewmodel.DetailMovieVM
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.android.ext.android.inject

class FragmentChiTietPhim : Fragment() {
    private var binding: FrgChiTietPhimBinding? = null
    private val VIDEO_ID = "G82CgMekC5U"
    val viewModel : DetailMovieVM by inject<DetailMovieVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgChiTietPhimBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding?.youtubePlayerView?.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                youTubePlayer.loadVideo(VIDEO_ID, 0f)
//            }
//        })

        viewModel.getDetailMovie(viewModel.movieId.value ?: 0)

        viewModel.getTheLoai()

        viewModel.detailMovie.observe(viewLifecycleOwner){
            binding?.tvTitle?.text = viewModel.detailMovie.value?.title
            Glide.with(requireContext()).load("https://image.tmdb.org/t/p/original/"+viewModel.detailMovie.value?.backdropPath).into(binding?.ivBackground!!)
            Glide.with(requireContext()).load("https://image.tmdb.org/t/p/original/"+viewModel.detailMovie.value?.poster_path).into(binding?.ivPoster!!)
            binding?.tvYear?.text = viewModel.detailMovie.value?.release_date?.split("-")?.get(0) ?: "n/a"
            binding?.circularProgressbar?.progress = viewModel.detailMovie.value?.voteAverage?.times(10)?.toInt() ?: 0
            binding?.tvDiem?.text = viewModel.detailMovie.value?.voteAverage?.times(10)?.toInt()?.toString() + "%"
            binding?.tvTheLoai?.text = viewModel.detailMovie.value?.genres?.get(0)?.name
            binding?.tvTomTat?.text = viewModel.detailMovie.value?.overview
        }

    }

}