package com.nikolas.astroweather

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.nikolas.astroweather.Database.DatabaseHelper
import com.nikolas.astroweather.Database.model.Node
import com.nikolas.astroweather.VM.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {
    var ppid :String = "e0acab86e57a4e959d0dec63a58d0bbc"
    var url : String = "https://api.openweathermap.org/data/2.5/weather?q=Opoczno" + "&appid=" + ppid
    val databaseHelper = DatabaseHelper(this,null)
    val sharedViewModel : SharedViewModel by lazy {
        ViewModelProviders.of(this).get(SharedViewModel::class.java)
    }

    var t1: Thread? = null
    var t2: Thread? = null
    lateinit var te :TextView
    lateinit var text1 : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text1 = text
        te = check

        if(te.text.equals("te")){
            supportFragmentManager.beginTransaction().add(R.id.fragA, Fragment_menu()).commit()
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                supportFragmentManager.beginTransaction().add(R.id.fragB, Fragment_menu()).commit()
            }
        }else{
            supportFragmentManager.beginTransaction().add(R.id.fragB, Fragment_Opcje()).commit()
            supportFragmentManager.beginTransaction().add(R.id.fragD, Fragment_A()).commit()
            supportFragmentManager.beginTransaction().add(R.id.fragC, Fragment_B()).commit()
        }

        t1 = object : Thread() {
            override fun run() {
                try {
                    while (!isInterrupted) {
                        Thread.sleep(1000)
                        runOnUiThread {
                            text1.text = Calendar.getInstance().time.toString()
                            //println("Update time")
                        }
                    }
                } catch (e: InterruptedException) {
                }

            }
        }
        (t1 as Thread).start()

        t2 = object : Thread() {
            override fun run() {
                try {
                    while (!isInterrupted) {
                        Thread.sleep(sharedViewModel.date.value!!.toLong()*1000)
                        runOnUiThread {
                            if(sharedViewModel.bool.value == false){
                                sharedViewModel.bool.value = true
                            }else{
                                sharedViewModel.bool.value = false
                            }
                            //println("update")
                        }
                    }
                } catch (e: InterruptedException) {
                }

            }
        }
        (t2 as Thread).start()


        observe(sharedViewModel)
        println("dsfdf")
        jsonRequest()
        println("hghdghghg")
    }


//    "opcjePog"
//
//        "pod"
//
//            "roz"
//
//                "pro"

    private fun observe(sharedViewModel: SharedViewModel) {
        sharedViewModel.who.observe(this, Observer {
            it?.let {
                if(it.equals("opcje")){
                    supportFragmentManager.beginTransaction().replace(R.id.fragA, Fragment_Opcje()).commit()
                }
                if(it.equals("menu")){
                    supportFragmentManager.beginTransaction().replace(R.id.fragA, Fragment_menu()).commit()
                }
                if ( it.equals("slace")){
                    supportFragmentManager.beginTransaction().replace(R.id.fragA, Fragment_A()).commit()
                }
                if(it.equals("ksie")){
                    supportFragmentManager.beginTransaction().replace(R.id.fragA, Fragment_B()).commit()
                }
                if(it.equals("opcjePog")){
                    supportFragmentManager.beginTransaction().replace(R.id.fragA,Fragment_weather_opcje()).commit()
                }
                if(it.equals("pod")){
                    supportFragmentManager.beginTransaction().replace(R.id.fragA, Fragment_weather_a()).commit()
                }
                if(it.equals("roz")){
                    supportFragmentManager.beginTransaction().replace(R.id.fragA, Fragment_weather_b()).commit()
                }
                if(it.equals("pro")){
                    supportFragmentManager.beginTransaction().replace(R.id.fragA, Fragment_weather_c()).commit()
                }
                if(it.equals("menuWe")){
                    supportFragmentManager.beginTransaction().replace(R.id.fragA, Fragment_weather_menu()).commit()
                }
            }
        })
    }

    private fun jsonRequest() {
        // 1. Uzyskanie referencji do kolejki
        val queue = Volley.newRequestQueue(this)

        // 2. Utworzenie żądania
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            object : Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject) {
                    try {
                        var gson = Gson()
                        var currentWeatherData: CurrentWeatherData = gson.fromJson(response.toString(),CurrentWeatherData::class.java)
                        sharedViewModel.currentWeatherData.value = currentWeatherData
                        databaseHelper.updateNote(Node(1,response.toString()))
                        var new = gson.fromJson(databaseHelper.getNode(1).data,CurrentWeatherData::class.java)
                        VolleyLog.d(new.name)
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



data class CurrentWeatherData (

    @SerializedName("coord") val coord : Coord,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("base") val base : String,
    @SerializedName("main") val main : Main,
    @SerializedName("wind") val wind : Wind,
    @SerializedName("rain") val rain : Rain,
    @SerializedName("clouds") val clouds : Clouds,
    @SerializedName("dt") val dt : Int,
    @SerializedName("sys") val sys : Sys,
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("cod") val cod : Int
)


data class Wind (

    @SerializedName("speed") val speed : Double,
    @SerializedName("deg") val deg : Double
)

data class Weather (

    @SerializedName("id") val id : Int,
    @SerializedName("main") val main : String,
    @SerializedName("description") val description : String,
    @SerializedName("icon") val icon : String
)

data class Sys (

    @SerializedName("message") val message : Double,
    @SerializedName("country") val country : String,
    @SerializedName("sunrise") val sunrise : Int,
    @SerializedName("sunset") val sunset : Int
)

data class Rain (

    @SerializedName("3h") val h3 : Double
)

data class Main (

    @SerializedName("temp") val temp : Double,
    @SerializedName("pressure") val pressure : Double,
    @SerializedName("humidity") val humidity : Int,
    @SerializedName("temp_min") val temp_min : Double,
    @SerializedName("temp_max") val temp_max : Double,
    @SerializedName("sea_level") val sea_level : Double,
    @SerializedName("grnd_level") val grnd_level : Double
)

data class Coord (

    @SerializedName("lon") val lon : Double,
    @SerializedName("lat") val lat : Double
)

data class Clouds(

    @SerializedName("all") val all: Int
)