package com.soundsstories.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soundsstories.Sound
import com.soundsstories.SoundPage
import com.soundsstories.SoundTemplate

class PageViewModel : ViewModel() {

    private val _backgroundColor = MutableLiveData<String>()
    val backgroundColor: LiveData<String> = _backgroundColor

    private val _soundlist = MutableLiveData<List<Sound>>()
    val soundlist: LiveData<List<Sound>> = _soundlist

    fun setTitle(title: SoundPage) {
        _backgroundColor.value = title.color
        _soundlist.value = title.sound
    }
}