package com.nikolas.astroweather

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.astrocalculator.AstroCalculator
import com.astrocalculator.AstroDateTime
import com.nikolas.astroweather.VM.SharedViewModel
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_a.view.*
import java.util.*
import android.graphics.Bitmap



class Fragment_A : Fragment() {


    lateinit var textView: TextView
    lateinit var timeText:TextView
    lateinit var sunRise:TextView
    lateinit var sunSet:TextView
    lateinit var twilateXD:TextView
    lateinit var button : Button
    lateinit var coButton:Button
    private lateinit var model: SharedViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.fragment_a,container,false)


        textView = v.textView
        coButton = v.co
        timeText = v.time
        sunRise = v.sunRise
        sunSet = v.sunSet
        twilateXD = v.twilate

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            model = ViewModelProviders.of(it).get(SharedViewModel::class.java)
        }

        coButton.setOnClickListener {
            model.who.value = "menu"
        }
        sunRise.setText("czas: " + model.getAstroCalculator()!!.sunInfo.sunrise.hour.toString()
                +":"+model.getAstroCalculator()!!.sunInfo.sunrise.minute.toString()
                + " azmut" + model.getAstroCalculator()!!.sunInfo.azimuthRise)
        sunSet.setText("czas: " + model.getAstroCalculator()!!.sunInfo.sunset.hour.toString()
                +":"+model.getAstroCalculator()!!.sunInfo.sunset.minute.toString()
                + " azmut" + model.getAstroCalculator()!!.sunInfo.azimuthSet)
        twilateXD.setText("czas zmierzchu: " + model.getAstroCalculator()!!.sunInfo.twilightEvening.hour.toString()
                +":"+model.getAstroCalculator()!!.sunInfo.twilightEvening.minute.toString() + " czas swit" + model.getAstroCalculator()!!.sunInfo.twilightMorning.hour
                +":"+ model.getAstroCalculator()!!.sunInfo.twilightMorning.minute)

        observe(model)

    }

    private fun observe(sharedViewModel: SharedViewModel) {
        sharedViewModel.latitude.observe(this, Observer {
            it?.let {
                model.setLocation()
                sunRise.setText("czas: " + model.getAstroCalculator()!!.sunInfo.sunrise.hour.toString()
                        +":"+model.getAstroCalculator()!!.sunInfo.sunrise.minute.toString()
                        + " azmut" + model.getAstroCalculator()!!.sunInfo.azimuthRise)
                sunSet.setText("czas: " + model.getAstroCalculator()!!.sunInfo.sunset.hour.toString()
                        +":"+model.getAstroCalculator()!!.sunInfo.sunset.minute.toString()
                        + " azmut" + model.getAstroCalculator()!!.sunInfo.azimuthSet)
                twilateXD.setText("czas zmierzchu: " + model.getAstroCalculator()!!.sunInfo.twilightEvening.hour.toString()
                        +":"+model.getAstroCalculator()!!.sunInfo.twilightEvening.minute.toString() + " czas swit" + model.getAstroCalculator()!!.sunInfo.twilightMorning.hour
                        +":"+ model.getAstroCalculator()!!.sunInfo.twilightMorning.minute)
            }
        })

        sharedViewModel.bool.observe(this, Observer {
            it?.let {
                model.setLocation()
                model.setTime()
                sunRise.setText("czas: " + model.getAstroCalculator()!!.sunInfo.sunrise.hour.toString()
                        +":"+model.getAstroCalculator()!!.sunInfo.sunrise.minute.toString()
                        + " azmut" + model.getAstroCalculator()!!.sunInfo.azimuthRise)
                sunSet.setText("czas: " + model.getAstroCalculator()!!.sunInfo.sunset.hour.toString()
                        +":"+model.getAstroCalculator()!!.sunInfo.sunset.minute.toString()
                        + " azmut" + model.getAstroCalculator()!!.sunInfo.azimuthSet)
                twilateXD.setText("czas zmierzchu: " + model.getAstroCalculator()!!.sunInfo.twilightEvening.hour.toString()
                        +":"+model.getAstroCalculator()!!.sunInfo.twilightEvening.minute.toString() + " czas swit" + model.getAstroCalculator()!!.sunInfo.twilightMorning.hour
                        +":"+ model.getAstroCalculator()!!.sunInfo.twilightMorning.minute)
            }
        })
    }
}