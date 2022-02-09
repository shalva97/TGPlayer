package com.example.tgplayer

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloaderActivity : AppCompatActivity() {

    private val viewModel by viewModels<DownloaderActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIntent()
        finish()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val data = intent?.getStringExtra(Intent.EXTRA_TEXT)
        if (data != null) {
            viewModel.startAudioDownload(data)
        }
    }

    private fun checkIntent() {
        if (intent?.action == Intent.ACTION_SEND && "text/plain" == intent.type) {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                viewModel.startAudioDownload(it)
            }
        }
    }
}
