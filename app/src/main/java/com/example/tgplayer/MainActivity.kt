package com.example.tgplayer

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomappbar.BottomAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setSupportActionBar(findViewById(R.id.main_toolbar))

        findViewById<BottomAppBar>(R.id.bottomAppBar)
            .setOnMenuItemClickListener {
                if (it.itemId == R.id.addPlaylist) {
                    findNavController(R.id.nav_host_fragment)
                        .navigate(R.id.action_homeFragment_to_addPlaylistFragment)
                }
                true
            }
    }
}
