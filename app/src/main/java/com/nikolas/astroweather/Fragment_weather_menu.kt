package com.nikolas.astroweather

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.nikolas.astroweather.VM.SharedViewModel
import kotlinx.android.synthetic.main.fragment_menu.view.*
import kotlinx.android.synthetic.main.fragment_weather_menu.view.*

class Fragment_weather_menu : Fragment() {
    lateinit var buttonOpcje: Button
    lateinit var buttonPod: Button
    lateinit var buttonRoz: Button
    lateinit var buttonPro: Button
    private  lateinit var model: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.fragment_weather_menu,container,false)

        buttonOpcje = v.opcje
        buttonPod = v.pod
        buttonRoz = v.roz
        buttonPro = v.pro

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}