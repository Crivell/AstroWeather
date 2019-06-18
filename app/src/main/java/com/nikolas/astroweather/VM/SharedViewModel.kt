package com.nikolas.astroweather.VM

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val latitude = MutableLiveData<Double>()
    val longitude = MutableLiveData<Double>()
    val odswierzanie = MutableLiveData<Int>()
    val text = MutableLiveData<String>()
    val who = MutableLiveData<String>()

    fun setText(tex:String){
        text.value = tex
    }
    init {
        text.value = ""
        who.value = "menu"
        odswierzanie.value = 1
    }
    fun getText(): LiveData<String> {
        return text
    }
}