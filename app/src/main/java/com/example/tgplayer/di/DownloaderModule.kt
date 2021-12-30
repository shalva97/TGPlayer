package com.example.tgplayer.di

import com.example.tgplayer.repository.DownloaderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.ServiceList
import org.schabi.newpipe.extractor.downloader.Downloader
import org.schabi.newpipe.extractor.services.youtube.YoutubeService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DownloaderModule {

    @Singleton
    @Provides
    fun downloaderForYTExtractor(client: OkHttpClient): Downloader {
        return DownloaderImpl(client)
    }

    @Singleton
    @Provides
    fun youtubeExtractor(downloader: Downloader): YoutubeService {
        NewPipe.init(downloader)
        return ServiceList.YouTube
    }
}