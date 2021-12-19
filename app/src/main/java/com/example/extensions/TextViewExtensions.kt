package com.example.extensions

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

fun ImageView.load(uri: String){
    Glide.with(this.context).load(uri).into(this)
}