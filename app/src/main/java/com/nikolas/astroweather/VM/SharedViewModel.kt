package com.nikolas.astroweather.VM

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.astrocalculator.AstroCalculator
import com.astrocalculator.AstroDateTime
import java.time.LocalDateTime
import java.util.*

class SharedViewModel : ViewModel() {
    val latitude = MutableLiveData<Double>()
    val longitude = MutableLiveData<Double>()
    val odswierzanie = MutableLiveData<Int>()
    val text = MutableLiveData<String>()
    val who = MutableLiveData<String>()
    val astroCalculator:MutableLiveData<AstroCalculator> = MutableLiveData<AstroCalculator>()
    fun setText(tex:String){
        text.value = tex
    }
    init {
        text.value = ""
        who.value = "menu"
        odswierzanie.value = 1
        astroCalculator.value = setUpDataToAstro(0.0,0.0)
    }
    fun getText(): LiveData<String> {
        return text
    }

    fun getAstroCalculator(): AstroCalculator? {
        return astroCalculator.value
    }

    fun setUpDataToAstro(latitude:Double, longitude:Double): AstroCalculator {
        val date:Date = Calendar.getInstance().time

        val astroDateTime: AstroDateTime = AstroDateTime(
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DAY_OF_MONTH,
            Calendar.HOUR,
            Calendar.MINUTE,
            Calendar.SECOND,
            Calendar.ZONE_OFFSET,false)
        val mLocation : AstroCalculator.Location = AstroCalculator.Location(latitude,longitude)
        return AstroCalculator(astroDateTime,mLocation)

    }
}