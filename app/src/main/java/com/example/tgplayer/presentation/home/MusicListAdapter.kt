package com.example.tgplayer.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.extensions.load
import com.example.tgplayer.databinding.MusicListItemBinding
import com.example.tgplayer.model.Audio

class MusicListAdapter(private val musicClick: ()-> Unit):RecyclerView.Adapter<MusicListAdapter.ViewHolder>() {


    var data: List<Audio> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicListAdapter.ViewHolder {
        return ViewHolder(MusicListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MusicListAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount()=data.size

    inner class ViewHolder(private val binding: MusicListItemBinding):RecyclerView.ViewHolder(binding.root){

        lateinit var currentAudio: Audio

        fun bind(){
            currentAudio = data[adapterPosition]
            binding.apply {
                musicImgView.load(currentAudio.thumbnail)
                musicNameTxt.text = currentAudio.name
                musicLengthTxt.text = currentAudio.length.toString()
            }
        }
    }

}