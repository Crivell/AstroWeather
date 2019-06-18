package com.nikolas.astroweather

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.nikolas.astroweather.VM.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.R.attr.start
import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_a.*


class MainActivity : AppCompatActivity() {

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
            supportFragmentManager.beginTransaction().add(R.id.fragA, Fragment_menu()).commit()
            supportFragmentManager.beginTransaction().add(R.id.fragB, Fragment_menu()).commit()
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                supportFragmentManager.beginTransaction().add(R.id.fragB, Fragment_menu()).commit()
            }
        }

        t1 = object : Thread() {
            override fun run() {
                try {
                    while (!isInterrupted) {
                        Thread.sleep(1000)
                        runOnUiThread {
                            text1.text = Calendar.getInstance().time.toString()
                            println("Update time")
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
                            println("update")
                        }
                    }
                } catch (e: InterruptedException) {
                }

            }
        }
        (t2 as Thread).start()
        observe(sharedViewModel)

    }


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
            }
        })
    }
}