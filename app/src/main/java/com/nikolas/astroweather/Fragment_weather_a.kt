package com.nikolas.astroweather

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.nikolas.astroweather.VM.SharedViewModel
import kotlinx.android.synthetic.main.fragment_weather_a.view.*

class Fragment_weather_a:Fragment() {

    lateinit var model:SharedViewModel
    lateinit var buttonBack:Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.fragment_weather_a,container,false)
        buttonBack = v.co

        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            model = ViewModelProviders.of(it).get(SharedViewModel::class.java)
        }

        buttonBack.setOnClickListener {
            model.who.value = "menuWe"
        }

    }
}

