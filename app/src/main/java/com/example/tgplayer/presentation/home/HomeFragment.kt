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
import com.example.tgplayer.databinding.ItemDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        val viewModel by viewModels<HomeViewModel>()
        viewModel.dataManipulation()




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
        getMusicLink(viewModel)

    }

    private fun addPlayList() {
        val dialogBinding = ItemDialogBinding.inflate(layoutInflater)
        showAddPlayListDialog(dialogBinding)
    }

    private fun showAddPlayListDialog(dialogBinding: ItemDialogBinding) {
        val dialog = Dialog(requireContext())
        dialog.setUp(dialogBinding)
        dialogBinding.apply {
            cancelBtn.setOnClickListener {
                dialog.cancel()
            }
            SaveBtn.setOnClickListener {

                makeAndSaveNewplayList(playListNameEditTxt.text.toString())
                Toast.makeText(
                    requireContext(),
                    "${playListNameEditTxt.text} is Saved!!",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.cancel()
            }
        }
    }

    private fun makeAndSaveNewplayList(playListName: String) {
        Toast.makeText(
            requireContext(),
            "Save playlist in room not yet implemented",
            Toast.LENGTH_SHORT
        ).show()

    }


    fun openYoutubeApp(text: String) {

        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/results?search_query=$text")
        )
        startActivity(browserIntent)
        requireActivity().finish()
    }


    private fun toastYoutubeMusicLink(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()

        }
    }


    fun getMusicLink(viewModel: HomeViewModel) {

        if (requireActivity().intent?.action == Intent.ACTION_SEND) {
            if ("text/plain" == requireActivity().intent.type) {

                requireActivity().intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                    viewModel.getAudioSourceFromYoutubeLink(it)
                }


            }
        }
    }


}