package com.example.tachiyomi.frg

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.Glide
import com.example.tachiyomi.adapter.CallBackGoToDetail
import com.example.tachiyomi.adapter.DienVienAdapter
import com.example.tachiyomi.adapter.GenreAdapter
import com.example.tachiyomi.adapter.TruyenAdapter
import com.example.tachiyomi.databinding.FrgChiTietPhimBinding
import com.example.tachiyomi.viewmodel.DaHoanThanhVM
import com.example.tachiyomi.viewmodel.DetailMovieVM
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class FragmentChiTietPhim : Fragment(), CallBackGoToDetail {
    private var binding: FrgChiTietPhimBinding? = null
    val viewModel: DetailMovieVM by inject<DetailMovieVM>()

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
//


        setUpUI()
        listenAppEvent()
        listenLiveData()

    }

    private fun setUpUI() {
        viewModel.movieId.observe(viewLifecycleOwner) {
            viewModel.getDetailMovie(viewModel.movieId.value ?: 0)
            viewModel.getActor(viewModel.movieId.value ?: 0)
            viewModel.getListSimilarMovie(viewModel.movieId.value ?: 0)

        }
    }

    private fun listenLiveData() {
        viewModel.detailMovie.observe(viewLifecycleOwner) {
            binding?.tvTitle?.text = viewModel.detailMovie.value?.title
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/original/" + viewModel.detailMovie.value?.backdropPath)
                .into(binding?.ivBackground!!)
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/original/" + viewModel.detailMovie.value?.poster_path)
                .into(binding?.ivPoster!!)
            binding?.tvYear?.text =
                viewModel.detailMovie.value?.release_date?.split("-")?.get(0) ?: "n/a"
            binding?.circularProgressbar?.progress =
                viewModel.detailMovie.value?.voteAverage?.times(10)?.toInt() ?: 0
            binding?.tvDiem?.text =
                viewModel.detailMovie.value?.voteAverage?.times(10)?.toInt()?.toString() + "%"
            binding?.tvTomTat?.text = viewModel.detailMovie.value?.overview
            binding?.rycTheLoai?.adapter =
                GenreAdapter(requireContext(), viewModel.detailMovie.value?.genres ?: listOf())

            for (list in viewModel.detailMovie.value?.videos?.trailer ?: listOf()) {
                if (list.type == "Trailer") {
                    viewModel.keyVideo.value = list.key
                }
            }


        }
        viewModel.listSimilarMovie.observe(viewLifecycleOwner) {
            binding?.rycPhimTuongTu?.adapter = TruyenAdapter(
                requireContext(),
                viewModel.listSimilarMovie.value?.allMovie ?: listOf(),
                this
            )
        }
        viewModel.cast.observe(viewLifecycleOwner) {
            binding?.rycDienVien?.adapter =
                DienVienAdapter(requireContext(), viewModel.cast.value?.cast ?: listOf())
        }
    }

    private fun listenAppEvent() {
        binding?.let { binding ->
            binding.btnBack.setOnClickListener {
                binding.btnBack.startAnimation(
                    AnimationUtils.loadAnimation(
                        requireContext(),
                        androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                fragmentManager?.popBackStack()
            }
            binding.btnPlayTrailer.setOnClickListener {
                binding.ctrYoutube.visibility = View.VISIBLE
                binding.scContainer.visibility = View.GONE
                viewModel.detailMovie.observe(viewLifecycleOwner) {
                    binding.youtubePlayerView.addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            viewModel.keyVideo.observe(viewLifecycleOwner) {
                                runBlocking {
                                    delay(2000)
                                    youTubePlayer.loadVideo(
                                        viewModel.keyVideo.value?.toString() ?: "",
                                        0f
                                    )
                                }
                            }
                        }
                    })
                }
            }
            binding.btnClose.setOnClickListener {
                binding.ctrYoutube.visibility = View.GONE
                binding.scContainer.visibility = View.VISIBLE
                val tracker = YouTubePlayerTracker()
                binding.youtubePlayerView.enableBackgroundPlayback(false)

//                binding.youtubePlayerView.onStateChanged(viewLifecycleOwner, Lifecycle.Event.ON_STOP)
            }
        }
    }

    override fun goToDetail(movieId: Int) {
        viewModel.movieId.value = movieId
        setUpUI()
    }

}