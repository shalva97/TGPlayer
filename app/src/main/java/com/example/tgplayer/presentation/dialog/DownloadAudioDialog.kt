package com.example.tgplayer.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.tgplayer.R
import com.example.tgplayer.databinding.ItemDialogForDownloadAudioBinding

class DownloadAudioDialog: DialogFragment(R.layout.item_dialog_for_download_audio) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ItemDialogForDownloadAudioBinding.bind(view)



    }




}