package com.example.tgplayer.presentation.home

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
import com.example.tgplayer.R
import com.example.tgplayer.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        editTextObserverFunction(binding, viewModel)
        init(viewModel, binding)
    }

    private fun init(
        viewModel: HomeViewModel,
        binding: FragmentHomeBinding
    ) {
        val musicListAdapter = MusicListAdapter {
            Toast.makeText(requireContext(), "not yet implemented", Toast.LENGTH_SHORT).show()
        }
        viewModel.musicList.observe(viewLifecycleOwner) {
            musicListAdapter.data = it
        }
        binding.apply {
            musicRecyclerView.apply {
                adapter = musicListAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun editTextObserverFunction(
        binding: FragmentHomeBinding,
        viewModel: HomeViewModel
    ) {
        binding.tilSearchView.doOnTextChanged { text, _, _, _ ->
            val len = text?.length ?: 0
            if (len > 3) {
                viewModel.search(text.toString())
            } else {
                viewModel.finishSearch()
            }
        }
        binding.youtubeSearchBtn.setOnClickListener {
            openYoutubeApp(binding.tilSearchView.text.toString())
        }
        viewModel.showSearchIcon.observe(viewLifecycleOwner) {

            binding.youtubeSearchBtn.isVisible = it
        }
    }

    private fun openYoutubeApp(text: String) {
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/results?search_query=$text")
        )
        startActivity(browserIntent)
    }
}