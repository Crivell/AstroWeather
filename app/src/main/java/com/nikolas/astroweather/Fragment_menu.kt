package com.nikolas.astroweather

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.nikolas.astroweather.VM.SharedViewModel
import kotlinx.android.synthetic.main.fragment_b.view.*
import kotlinx.android.synthetic.main.fragment_menu.view.*

class Fragment_menu :Fragment() {

    lateinit var buttonMenu: Button
    lateinit var buttonSlace: Button
    lateinit var buttonKsiezyc: Button
    private  lateinit var model: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.fragment_menu,container,false)

        buttonMenu = v.zmiana
        buttonSlace = v.slonce
        buttonKsiezyc = v.ksienzyc

        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            model = ViewModelProviders.of(it).get(SharedViewModel::class.java)
        }

        buttonMenu.setOnClickListener {
            model.who.value = "opcje"
        }

        buttonSlace.setOnClickListener {
            model.who.value = "slace"
        }

        buttonKsiezyc.setOnClickListener {
            model.who.value = "ksie"
        }
    }

}