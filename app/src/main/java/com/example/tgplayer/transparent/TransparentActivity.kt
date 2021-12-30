package com.example.tgplayer.transparent

import android.app.Activity
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log.d
import android.util.Log.i
import androidx.appcompat.app.AppCompatActivity
import com.example.tgplayer.audiodownloadservice.DownloadManagerForAudio
import com.example.tgplayer.repository.YoutubeDownloaderRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class TransparentActivity : AppCompatActivity() {



    @Inject
     lateinit var youtubeDownloadRepository: YoutubeDownloaderRepository

     var downloadID:Long = -1






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val downloadManger = DownloadManagerForAudio()
        i("transparenLifycycle", "onCreate")
            registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager


        if (intent?.action == Intent.ACTION_SEND) {
            if ("text/plain" == intent.type) {

                intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                    d("youtubelinktag",it )

                    GlobalScope.launch{
                        withContext(Dispatchers.IO){

                            val audio = youtubeDownloadRepository.getAudio(it)

                            var request = DownloadManager.Request(Uri.parse(audio.audioSource))
                                .setTitle(audio.name)
                                .setDescription(audio.length.toString())
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                                .setAllowedOverMetered(true)
                                .setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC, "${audio.name}")
                                .setMimeType("audio/webm")





                            var dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                            downloadID = dm.enqueue(request)
                            dm.enqueue(request)
                            /*finish()*/

                        }

                    }

                }


            }
        }



    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onStart() {
        super.onStart()
        i("transparenLifycycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        i("transparenLifycycle", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        i("transparenLifycycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        i("transparenLifycycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        i("transparenLifycycle", "onDestroy")
    }



    val br = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            var id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (id == downloadID){
                i("transparenLifycycle", "broadcastReceiver")
            }

        }

    }

}