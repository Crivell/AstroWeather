package com.nikolas.astroweather

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
import kotlinx.android.synthetic.main.fragment_menu.view.*
import kotlinx.android.synthetic.main.fragment_opcje.*
import kotlinx.android.synthetic.main.fragment_opcje.view.*

class Fragment_Opcje :Fragment() {


    lateinit var buttonMenu: Button
    lateinit var buttonSlace: Button
    lateinit var buttonKsiezyc: Button
    lateinit var upButton: Button
    lateinit var odswierzanieButton: Button
    lateinit var kor1:EditText
    lateinit var kor2:EditText
    lateinit var updateText:TextView
    lateinit var odswiText:TextView
    private  lateinit var model: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val v : View = inflater.inflate(R.layout.fragment_opcje, container,false)

        updateText = v.up
        buttonSlace = v.sl
        buttonKsiezyc = v.ks
        upButton = v.update
        buttonMenu = v.co
        kor1 = v.kor1
        kor2 = v.kor2
        odswiText = v.odswie
        odswierzanieButton = v.updateOdswiezania
        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            model = ViewModelProviders.of(it).get(SharedViewModel::class.java)
        }

        buttonMenu.setOnClickListener {
            model.who.value = "menu"
        }

        buttonSlace.setOnClickListener {
            model.who.value = "slace"
        }

        buttonKsiezyc.setOnClickListener {
            model.who.value = "ksie"
        }

        upButton.setOnClickListener {
            if(kor1.text.length > 0 && kor2.text.length > 0){
                model.latitude.value = kor1.text.toString().toDouble()
                model.longitude.value = kor2.text.toString().toDouble()
                updateText.text = "Koordynaty zostaly zaktualizowane"
            }
        }
        odswierzanieButton.setOnClickListener {
            if(odswiText.text.length > 0){
                model.odswierzanie.value = odswiText.text.toString().toInt()
                updateText.text = "Odzwierzanie zostalo zmienione na wartość: " + odswiText.text.toString()
            }
        }

    }
}