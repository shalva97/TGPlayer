package com.example.tgplayer.home

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.extensions.dp
import com.example.tgplayer.databinding.PlaylistAddItemBinding
import com.example.tgplayer.databinding.PlaylistItemBinding
import com.example.tgplayer.model.PlayList
import kotlin.math.exp


class PlayListAdapter(private val click: (playListName: String, playListPosition: Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var data: List<PlayList> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {

        return if (position == data.size) {
            1
        } else {
            2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            AddPlayListViewHolder(
                PlaylistAddItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            PlayListViewHolder(
                PlaylistItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PlayListViewHolder -> {
                holder.bind()
            }
            is AddPlayListViewHolder -> {
                holder.bind()

            }
            else -> {
                throw IllegalArgumentException("not real type")
            }
        }
    }


    override fun getItemCount() = data.size + 1


    fun fixSize(v: View, selected: Boolean) {
        val normalSize = 70.dp
        val expandSize = 90.dp
        val layoutParams: ViewGroup.LayoutParams = v.layoutParams

        if (selected) {
            layoutParams.height = expandSize
            v.layoutParams = layoutParams
        } else {
            layoutParams.height = normalSize
            v.layoutParams = layoutParams
        }

    }


    inner class PlayListViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var currentData: PlayList
        fun bind() {

            binding.root.setOnClickListener(this)
            currentData = data[adapterPosition]
            fixSize(binding.playlistView, currentData.selected)

            val contextCompat = ContextCompat.getColor(itemView.context, currentData.color)
            binding.playlistNameTxt.text = currentData.name
            binding.playlistView.backgroundTintList = ColorStateList.valueOf(contextCompat)


        }

        override fun onClick(v: View?) {
            if (data[adapterPosition].selected) {
                return
            } else {
                click.invoke(currentData.name, adapterPosition)
            }


        }
    }


    inner class AddPlayListViewHolder(private val binding: PlaylistAddItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(){
            binding.root.setOnClickListener(this)


        }

        override fun onClick(v: View?) {
            click.invoke("add", 0)
        }
    }


}