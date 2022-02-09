package com.example.tgplayer.presentation.home

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.extensions.setUp
import com.example.tgplayer.R
import com.example.tgplayer.databinding.FragmentHomeBinding
import com.example.tgplayer.databinding.ItemDialogForDownloadAudioBinding
import com.example.tgplayer.model.Audio
import dagger.hilt.android.AndroidEntryPoint
import org.apache.commons.lang3.time.DurationFormatUtils

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        viewModel.dataManipulation()
        EditTextObserverFunction(binding, viewModel)
        init(viewModel, binding)
    }

    fun getDurationFormat(long: Long): String {
        return DurationFormatUtils.formatDuration(long, "HH:mm:SSS", false)
    }

    private fun init(
        viewModel: HomeViewModel,
        binding: FragmentHomeBinding
    ) {
        val playListAdapter = PlayListAdapter { playListName, playListPosition ->

            if (playListName == "add") addPlayList() else viewModel.itemClicked(playListPosition)

        }

        val musicListAdapter = MusicListAdapter {
            Toast.makeText(requireContext(), "not yet implemented", Toast.LENGTH_SHORT).show()
        }

        viewModel.playListName.observe(viewLifecycleOwner) {
            binding.playListTxt.text = it.name
        }


        viewModel.playList.observe(viewLifecycleOwner) {
            playListAdapter.data = it

        }
        viewModel.musicList.observe(viewLifecycleOwner) {
            musicListAdapter.data = it
        }
//        viewModel.audios.observe(viewLifecycleOwner) {
//            musicListAdapter.data = it
//        } TODO

        binding.apply {
            playListRecyclerView.apply {
                adapter = playListAdapter
                val linearLayoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                layoutManager = linearLayoutManager
            }
            musicRecyclerView.apply {
                adapter = musicListAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }


        }
    }

    private fun EditTextObserverFunction(
        binding: FragmentHomeBinding,
        viewModel: HomeViewModel
    ) {
        binding.tipSearchView.doOnTextChanged { text, start, before, count ->
            val len = text?.length ?: 0
            if (len > 3) {
                viewModel.search(text.toString())
            } else {
                viewModel.finishSearch()
            }
        }
        binding.youtubeSearchBtn.setOnClickListener {

            openYoutubeApp(binding.tipSearchView.text.toString())

        }
        viewModel.showSearchIcon.observe(viewLifecycleOwner) {

            binding.youtubeSearchBtn.isVisible = it
        }
    }

    fun addPlayList() {
        /*not yet implemented*/
    }


    fun openYoutubeApp(text: String) {

        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/results?search_query=$text")
        )
        startActivity(browserIntent)
    }


}