package com.example.tgplayer.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tgplayer.R
import com.example.tgplayer.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        val viewModel by viewModels<HomeViewModel>()
        viewModel.dataManipulation()


        EditTextObserverFunction(binding, viewModel)

        init(viewModel, binding)


    }


    private fun init(
        viewModel: HomeViewModel,
        binding:  FragmentHomeBinding
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
        viewModel.droebitiData.observe(viewLifecycleOwner) {
            musicListAdapter.data = it
        }

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
        binding:  FragmentHomeBinding,
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

    fun addPlayList(){
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