package com.example.tachiyomi.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.tachiyomi.databinding.FrgChiTietPhimBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class FragmentChiTietPhim : Fragment() {
    private var binding: FrgChiTietPhimBinding? = null
    private val VIDEO_ID = "G82CgMekC5U"
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


    }

}