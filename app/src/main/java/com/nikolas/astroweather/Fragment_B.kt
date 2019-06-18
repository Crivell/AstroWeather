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

    lateinit var textView: TextView
    lateinit var button : Button
    lateinit var coButton:Button
    private  lateinit var model: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.fragment_b,container,false)


        button = v.butB
        textView = v.tex
        coButton = v.co
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            model = ViewModelProviders.of(it).get(SharedViewModel::class.java)
        }

        coButton.setOnClickListener {
            model.who.value = "menu";
        }
    }


private fun observe(sharedViewModel: SharedViewModel) {
    sharedViewModel.text.observe(this, Observer {
        it?.let {
            textView.text = it
        }
    })
}
}