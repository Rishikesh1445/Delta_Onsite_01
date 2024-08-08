package com.example.reboundgpt

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.reboundgpt.dataClass.ToDisplay
import androidx.compose.runtime.State

class viewModel:ViewModel() {

    private val _toDisplay = mutableStateOf(ToDisplay())
    val toDisplay : State<ToDisplay> = _toDisplay

    fun submitButton(text:String){
        val dummy = listOf(text)
        _toDisplay.value = _toDisplay.value.copy(texts = toDisplay.value.texts+dummy)
    }

    fun copyButton(textToCopy:String, context: Context){
        val clipboardManager: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", textToCopy)
        clipboardManager.setPrimaryClip(clip)
    }

    fun retryButton(){
        val dummy = listOf(toDisplay.value.texts.last())
        _toDisplay.value = _toDisplay.value.copy(texts = toDisplay.value.texts+dummy)
    }
}