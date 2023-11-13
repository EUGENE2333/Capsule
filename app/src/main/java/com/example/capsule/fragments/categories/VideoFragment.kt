package com.example.capsule.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.capsule.R
import com.example.capsule.databinding.FragmentVideoBinding
import com.example.capsule.data.util.VideoUrls
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource


class VideoFragment : Fragment(R.layout.fragment_video) {

    private lateinit var binding: FragmentVideoBinding
    private var onNextButtonClickListener: OnNextVideoButtonClickListener? = null
    private var exoplayer: ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = true



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePlayer()
        binding.nextButton.setOnClickListener {
            onNextButtonClickListener?.onNextVideoButtonClicked()
        }


    }


    // Video Player
    fun preparePlayer() {
        exoplayer = ExoPlayer.Builder(requireContext()).build()
        exoplayer?.playWhenReady =  true
        binding.playerView.player = exoplayer
        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaItem = MediaItem.fromUri(VideoUrls.URL)
        val mediaSource = DashMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(mediaItem)
        exoplayer?.setMediaSource(mediaSource)
        exoplayer?.seekTo(playbackPosition)
        exoplayer?.playWhenReady = playWhenReady
        exoplayer?.prepare()
    }

    fun pausePlayer(){
        exoplayer?.let{player->
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady
            player.pause()

        }

    }

    fun releasePlayer(){
        exoplayer?.let{player->
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady
            player.release()
            exoplayer = null

        }

    }

    fun setOnNextButtonClickListener(listener: OnNextVideoButtonClickListener) {
        onNextButtonClickListener = listener
    }

    override fun onStop() {
        super.onStop()
        pausePlayer()
    }

    override fun onPause() {
        super.onPause()
        pausePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
      releasePlayer()
    }

    interface OnNextVideoButtonClickListener {
        fun onNextVideoButtonClicked()
    }
}
