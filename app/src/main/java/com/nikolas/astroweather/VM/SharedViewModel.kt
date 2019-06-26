package com.nikolas.astroweather.VM

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.astrocalculator.AstroCalculator
import com.astrocalculator.AstroDateTime
import com.nikolas.astroweather.CurrentWeatherData
import java.time.LocalDateTime
import java.util.*

class SharedViewModel : ViewModel() {
    val latitude = MutableLiveData<Double>()
    val longitude = MutableLiveData<Double>()
    val odswierzanie = MutableLiveData<Int>()
    val text = MutableLiveData<String>()
    val who = MutableLiveData<String>()
    val date = MutableLiveData<Int>()
    val bool = MutableLiveData<Boolean>()
    val astroCalculator:MutableLiveData<AstroCalculator> = MutableLiveData<AstroCalculator>()
    val currentWeatherData:MutableLiveData<CurrentWeatherData> = MutableLiveData()
    val farOrCel:MutableLiveData<Boolean> = MutableLiveData()

    fun setText(tex:String){
        text.value = tex
    }
    init {
        latitude.value = 0.0
        longitude.value = 0.0
        text.value = ""
        who.value = "menu"
        odswierzanie.value = 1
        astroCalculator.value = setUpDataToAstro(0.0,0.0)
        date.value = 15
        bool.value = false
        farOrCel.value = false
    }
    fun getText(): LiveData<String> {
        return text
    }

    fun getCurrentWeatherData():CurrentWeatherData?{
        return currentWeatherData.value
    }

    fun getAstroCalculator(): AstroCalculator? {
        return astroCalculator.value
    }

    fun getLa(): Double? {
        return this.latitude.value
    }
    fun getLo(): Double? {
        return this.longitude.value
    }

    fun setUpDataToAstro(latitude:Double, longitude:Double): AstroCalculator {
        val date:Date = Calendar.getInstance().time

        val astroDateTime: AstroDateTime = AstroDateTime(
            date.year,
            date.month,
            date.day,
            date.hours,
            date.minutes,
            date.seconds,
            date.timezoneOffset,false)
        val mLocation : AstroCalculator.Location = AstroCalculator.Location(latitude,longitude)
        return AstroCalculator(astroDateTime,mLocation)

    }

    fun setLocation(){
        val mLocation : AstroCalculator.Location = AstroCalculator.Location(getLa() ?: 0.0,getLo() ?: 0.0)
        getAstroCalculator()!!.setLocation(mLocation)
    }

    fun setTime(){
        val date:Date = Calendar.getInstance().time
        val astroDateTime: AstroDateTime = AstroDateTime(
            date.year,
            date.month,
            date.day,
            date.hours,
            date.minutes,
            date.seconds,
            date.timezoneOffset,false)
        getAstroCalculator()!!.setDateTime(astroDateTime)
    }
}