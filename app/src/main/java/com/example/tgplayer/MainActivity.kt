package com.example.tgplayer

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tgplayer.databinding.ActivityMainBinding
import com.example.tgplayer.repository.IntentEvents
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var events: IntentEvents

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        val binding = ActivityMainBinding.bind(findViewById<ViewGroup>(R.id.content))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        events.youtubeLinkIntent.postValue(intent?.getStringExtra(Intent.EXTRA_TEXT))
    }
}

