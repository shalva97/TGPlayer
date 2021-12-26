package com.example.extensions

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import androidx.viewbinding.ViewBinding

fun Dialog.setUp(
    bind: ViewBinding,

){

    window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    window!!.requestFeature(Window.FEATURE_NO_TITLE)
    window!!.setContentView(bind.root)
    val params = window!!.attributes
    params.width = WindowManager.LayoutParams.MATCH_PARENT
    params.height = WindowManager.LayoutParams.WRAP_CONTENT
    this.show()


}