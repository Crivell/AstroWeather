package com.nikolas.astroweather

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.nikolas.astroweather.VM.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_b.*
import kotlinx.android.synthetic.main.fragment_b.view.*

class Fragment_B : Fragment() {

    lateinit var moonRise: TextView
    lateinit var moonSet: TextView
    lateinit var nowPel: TextView
    lateinit var daySyn: TextView
    lateinit var per: TextView
    lateinit var button: Button
    lateinit var coButton: Button
    private lateinit var model: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_b, container, false)



        moonRise = v.moonRise
        moonSet = v.moonSet
        nowPel = v.nowPel
        moonRise = v.moonRise
        per = v.per
        coButton = v.co
        daySyn = v.day
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
        moonRise.setText(
            "czas: " + model.getAstroCalculator()!!.moonInfo.moonrise.hour.toString()
                    + ":" + model.getAstroCalculator()!!.moonInfo.moonrise.minute.toString()
        )
        moonSet.setText(
            "czas: " + model.getAstroCalculator()!!.moonInfo.moonset.hour.toString()
                    + ":" + model.getAstroCalculator()!!.moonInfo.moonset.minute.toString()
        )
        nowPel.setText(
            "Now: " + model.getAstroCalculator()!!.moonInfo.nextNewMoon.toString()
                    + "\n Pełnia" + model.getAstroCalculator()!!.moonInfo.nextFullMoon.toString()
        )
        per.setText(
            model.getAstroCalculator()!!.moonInfo.age.toString() + "%"
        )

        daySyn.setText(
            model.getAstroCalculator()!!.moonInfo.illumination.toString()
        )
        observe(model)
    }


    private fun observe(sharedViewModel: SharedViewModel) {
        sharedViewModel.latitude.observe(this, Observer {
            it?.let {
                model.setLocation()
                moonRise.setText(
                    "czas: " + model.getAstroCalculator()!!.moonInfo.moonrise.hour.toString()
                            + ":" + model.getAstroCalculator()!!.moonInfo.moonrise.minute.toString()
                )
                moonSet.setText(
                    "czas: " + model.getAstroCalculator()!!.moonInfo.moonset.hour.toString()
                            + ":" + model.getAstroCalculator()!!.moonInfo.moonset.minute.toString()
                )
                nowPel.setText(
                    "Now: " + model.getAstroCalculator()!!.moonInfo.nextNewMoon.toString()
                            + "\n Pełnia" + model.getAstroCalculator()!!.moonInfo.nextFullMoon.toString()
                )
                per.setText(
                    model.getAstroCalculator()!!.moonInfo.age.toString() + "%"
                )

                daySyn.setText(
                    model.getAstroCalculator()!!.moonInfo.nextNewMoon.toString()
                )
            }
        })
        sharedViewModel.bool.observe(this, Observer {
            it?.let {
                model.setLocation()
                model.setTime()
                moonRise.setText(
                    "czas: " + model.getAstroCalculator()!!.moonInfo.moonrise.hour.toString()
                            + ":" + model.getAstroCalculator()!!.moonInfo.moonrise.minute.toString()
                )
                moonSet.setText(
                    "czas: " + model.getAstroCalculator()!!.moonInfo.moonset.hour.toString()
                            + ":" + model.getAstroCalculator()!!.moonInfo.moonset.minute.toString()
                )
                nowPel.setText(
                    "Now: " + model.getAstroCalculator()!!.moonInfo.nextNewMoon.toString()
                            + "\n Pełnia" + model.getAstroCalculator()!!.moonInfo.nextFullMoon.toString()
                )
                per.setText(
                    model.getAstroCalculator()!!.moonInfo.age.toString() + "%"
                )

                daySyn.setText(
                    model.getAstroCalculator()!!.moonInfo.nextNewMoon.toString()
                )
            }
        })
    }
}