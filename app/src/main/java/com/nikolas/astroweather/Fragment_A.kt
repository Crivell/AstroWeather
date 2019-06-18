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
import com.nikolas.astroweather.VM.SharedViewModel
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_a.view.*

class Fragment_A : Fragment() {

    lateinit var editText : EditText
    lateinit var textView: TextView
    lateinit var timeText:TextView
    lateinit var button : Button
    lateinit var coButton:Button
    private lateinit var model: SharedViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.fragment_a,container,false)

        editText = v.textA
        textView = v.textView
        button = v.butA
        coButton = v.co
        timeText = v.time

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            model = ViewModelProviders.of(it).get(SharedViewModel::class.java)
        }

        editText.setText(model.text.value)
        button.setOnClickListener{
            model.setText(editText.text.toString())
            textView.setText(model.text.value)
        }

        coButton.setOnClickListener {
            model.who.value = "menu"
        }


    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        model = activity?.run {
//            ViewModelProviders.of(this).get(SharedViewModel::class.java)
//        } ?: throw Exception("Invalid Activity")
//        model.text.observe(this, Observer<CharSequence> { item ->
//            editText.setText(item)
//        })
//         editText = textA
//        button = butA
//        button.setOnClickListener(){
//            model.setText(editText.text)
//        }
//    }
}