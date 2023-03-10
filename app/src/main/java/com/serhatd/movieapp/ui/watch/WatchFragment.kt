package com.serhatd.movieapp.ui.watch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.serhatd.movieapp.MainActivity
import com.serhatd.movieapp.R
import com.serhatd.movieapp.databinding.FragmentWatchBinding

class WatchFragment : Fragment() {
    private lateinit var binding: FragmentWatchBinding
    private lateinit var player: ExoPlayer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watch, container, false)
        (requireActivity() as MainActivity).binding.toolbar.visibility = View.GONE

        initPlayer()
        return binding.root
    }

    private fun initPlayer() {
        val args: WatchFragmentArgs by navArgs()
        val movie = args.movie

        player = ExoPlayer.Builder(requireContext()).build()
        binding.frgWatchVideoPlayer.player = player

        player.playWhenReady = true
        player.setMediaItem(MediaItem.fromUri(movie.movie_url))
        player.prepare()
    }

    private fun releasePlayer() {
        player.release()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }
}