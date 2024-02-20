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

    }

    private fun listenAppEvent() {
        binding?.btnClose?.setOnClickListener {
            fragmentManager?.popBackStack()
        }

    }

    private fun listentLiveData() {
        viewModel.detailMovie.observe(viewLifecycleOwner) {
            binding?.youtubePlayerView?.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {

                    for (list in viewModel.detailMovie.value?.videos?.trailer ?: listOf()) {
                        if (list.type == "Trailer") {
                            Toast.makeText(context, list.key.toString(), Toast.LENGTH_SHORT).show()
                            youTubePlayer.loadVideo(
                                list.key,
                                0f
                            )
                        }
                    }

                }
            })
        }
    }
}