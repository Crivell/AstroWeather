package com.nikolas.astroweather

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.nikolas.astroweather.VM.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val sharedViewModel : SharedViewModel by lazy {
        ViewModelProviders.of(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragA, Fragment_menu()).commit()


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