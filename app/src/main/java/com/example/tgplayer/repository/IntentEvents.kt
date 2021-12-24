package com.example.tgplayer.repository

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntentEvents @Inject constructor() {
    val youtubeLinkIntent = MutableLiveData<String>()
}