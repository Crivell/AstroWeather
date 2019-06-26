package com.nikolas.astroweather

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.nikolas.astroweather.Database.DatabaseHelper
import com.nikolas.astroweather.Database.model.Node
import com.nikolas.astroweather.VM.SharedViewModel

import kotlinx.android.synthetic.main.fragment_weather_opcje.view.*
import org.json.JSONException
import org.json.JSONObject

class Fragment_weather_opcje :Fragment() {

    private  lateinit var model: SharedViewModel
    lateinit var buttonMenu: Button
    lateinit var buttonOds :  Button
    lateinit var buttonPrze: Button
    lateinit var lok:String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_weather_opcje, container, false)
        buttonMenu = v.co
        buttonOds = v.sl
        buttonPrze = v.ks
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            model = ViewModelProviders.of(it).get(SharedViewModel::class.java)
        }
        if(model.farOrCel.value == true){
            buttonPrze.text = "Przelancz na Farenhaity"
        }else{
            buttonPrze.text = "Przelancz na celciusze"
        }

        buttonMenu.setOnClickListener {
            model.who.value = "menuWe"
        }

        buttonOds.setOnClickListener {
            model.who.value = "slace"
        }

        buttonPrze.setOnClickListener {
            if(model.farOrCel.value == false){
                buttonPrze.text = "Przelancz na Farenhaity"
                model.farOrCel.value=true
            }else{
                buttonPrze.text = "Przelancz na celciusze"
                model.farOrCel.value=false
            }
        }
    }

    private fun jsonRequest(lo:String) {

        var ppid :String = "e0acab86e57a4e959d0dec63a58d0bbc"
        var url : String = "https://api.openweathermap.org/data/2.5/weather?q="+ lo + "&appid=" + ppid
        // 1. Uzyskanie referencji do kolejki
        val queue = Volley.newRequestQueue(activity)

        // 2. Utworzenie żądania
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            object : Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject) {
                    try {
                        var gson = Gson()
                        var currentWeatherData: CurrentWeatherData = gson.fromJson(response.toString(),CurrentWeatherData::class.java)
                        model.currentWeatherData.value = currentWeatherData

                        VolleyLog.d(model.getCurrentWeatherData()!!.name)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    VolleyLog.e(error.message)
                }
            })

        // 3. Dodanie żądania na kolejkę.
        queue.add(jsonObjectRequest)
    }

}
