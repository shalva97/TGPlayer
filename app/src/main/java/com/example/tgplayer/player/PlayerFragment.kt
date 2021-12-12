package com.example.tgplayer.player

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.tgplayer.R
import com.example.tgplayer.databinding.FragmentPlayerBinding


class PlayerFragment : Fragment(R.layout.fragment_player) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model: ViewModel by viewModels()
        val binding = FragmentPlayerBinding.bind(view)



    }
}