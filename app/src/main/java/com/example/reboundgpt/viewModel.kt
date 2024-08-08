package com.example.reboundgpt

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.reboundgpt.dataClass.ToDisplay
import androidx.compose.runtime.State
import com.example.reboundgpt.dataClass.CustomString

class viewModel:ViewModel() {

    private val _toDisplay = mutableStateOf(ToDisplay())
    val toDisplay : State<ToDisplay> = _toDisplay

    fun submitButton(text:String){
        val dummy = listOf(CustomString(text, true))
        var prev = toDisplay.value.texts.toMutableList()
        val index = toDisplay.value.texts.lastIndex
        prev[index].isLast = false
        val final = prev+dummy
        _toDisplay.value = _toDisplay.value.copy(texts = final)
    }

    fun copyButton(textToCopy:String, context: Context){
        val clipboardManager: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", textToCopy)
        clipboardManager.setPrimaryClip(clip)
    }

    fun retryButton(){
        var prev = toDisplay.value.texts.toMutableList()
        val index = toDisplay.value.texts.lastIndex
        prev[index].isLast = false
        val dummy = listOf(toDisplay.value.texts.last())
        val final = prev+dummy
        val indexx = final.lastIndex
        final[indexx].isLast = true
        _toDisplay.value = _toDisplay.value.copy(texts = final)
    }
}

