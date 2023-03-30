package com.mypackage.colormaker

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.slider.Slider
import java.text.DecimalFormat
import android.text.Editable


class MainActivity : AppCompatActivity() {


    lateinit var greenSliderVal: EditText
    lateinit var redSliderVal: EditText
    lateinit var blueSliderVal: EditText

    lateinit var greenSwitch: SwitchCompat
    lateinit var redSwitch: SwitchCompat
    lateinit var blueSwitch: SwitchCompat
    lateinit var dontSwitch: SwitchCompat


    private val viewModel: MyViewModel by lazy {
        PreferencesDataStore.initialize(this)
        MyViewModel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        redSliderVal= findViewById(R.id.redSliderVal)
        greenSliderVal= findViewById(R.id.greenSliderVal)
        blueSliderVal= findViewById(R.id.blueSliderVal)

        val button = findViewById<Button>(R.id.button)

        val greenSlider= findViewById<Button>(R.id.greenSlider) as Slider
        val redSlider= findViewById<Button>(R.id.redSlider) as Slider
        val blueSlider= findViewById<Button>(R.id.blueSlider) as Slider
        val colorMaker= findViewById(R.id.colorMaker) as View

        blueSwitch= findViewById(R.id.blueSwitch) as SwitchCompat
        redSwitch= findViewById(R.id.redSwitch) as SwitchCompat
        greenSwitch= findViewById(R.id.greenSwitch) as SwitchCompat
        dontSwitch = findViewById(R.id.dontSwitch) as SwitchCompat

        val df = DecimalFormat("#.##")

        //loading inputs from datastore
        this.viewModel.loadInputs(this)


        //dont save activities switch listener
        dontSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        if(isChecked){
            this.viewModel.saveInput("", 1);

            this.viewModel.saveInput("", 2);

            this.viewModel.saveInput("", 3);

            this.viewModel.saveInput("true", 4);
             this.greenSwitch.setChecked(true)

            this.viewModel.saveInput("true", 5);
            this.redSwitch.setChecked(true)

            this.viewModel.saveInput("true", 6);
            this.blueSwitch.setChecked(true)

            this.greenSliderVal.setText(greenSlider.value.toString())
            this.redSliderVal.setText(redSlider.value.toString())
            this.blueSliderVal.setText(blueSlider.value.toString())
        }
            this.greenSliderVal.setText(greenSlider.value.toString())
            this.redSliderVal.setText(redSlider.value.toString())
            this.blueSliderVal.setText(blueSlider.value.toString())

        })


        // reset all values to zero
        button.setOnClickListener {
            val z = 0 // just a random variable to initialise textbox values
            greenSlider.value=0.0F
            redSlider.value=0.0F
            blueSlider.value=0.0F

            if(blueSlider.isEnabled == false) blueSlider.isEnabled=true
            if(redSlider.isEnabled == false) redSlider.isEnabled=true
            if(greenSlider.isEnabled == false) greenSlider.isEnabled=true
            if(blueSwitch.isChecked == false) blueSwitch.isChecked=true
            if(redSwitch.isChecked == false) redSwitch.isChecked=true
            if(greenSwitch.isChecked == false) greenSwitch.isChecked=true
            if(greenSliderVal.isEnabled==false) this.greenSliderVal.isEnabled=true
            if(blueSliderVal.isEnabled==false) this.blueSliderVal.isEnabled=true
            if(redSliderVal.isEnabled==false) this.redSliderVal.isEnabled=true

            this.greenSliderVal.setText("")
            this.blueSliderVal.setText("")
            this.redSliderVal.setText("")

            colorMaker.setBackgroundColor(Color.rgb(0, 0, 0)) // resetting r,g,b values to 0,0,0
        }


        //listener for green value changes
        this.greenSliderVal.doOnTextChanged { text, _, _, _ ->
            try {

                if(text!=null)
                {
                    val value = greenSliderVal.text.toString().toFloat()

                    if (value >= 0 && value <= 1) {
                    if(!dontSwitch.isChecked())
                    {
                    this.viewModel.saveInput(this.greenSliderVal.text.toString(),1)
                    }

                        greenSlider.value = value //syncing slider value with the edit text value


                    val r = redSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                    val g = greenSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                    val b = blueSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                    val red = r.toInt()
                    val blue = b.toInt()
                    val green = g.toInt()
                    colorMaker.setBackgroundColor(Color.rgb(red, green, blue))
                }}
            }
            catch(e:java.lang.NumberFormatException){
                 }
        }

        //listener for red value changes


        this.redSliderVal.doOnTextChanged { text, _, _, _ ->
            try {

                if(text!=null)
                {
                    val value = redSliderVal.text.toString().toFloat()

                    if (value >= 0 && value <= 1) {
                        if(!dontSwitch.isChecked())
                        {
                            this.viewModel.saveInput(this.redSliderVal.text.toString(),2)
                        }

                        redSlider.value = value //syncing slider value with the edit text value


                        val r = redSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                        val g = greenSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                        val b = blueSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                        val red = r.toInt()
                        val blue = b.toInt()
                        val green = g.toInt()
                        colorMaker.setBackgroundColor(Color.rgb(red, green, blue))
                    }}
            }
            catch(e:java.lang.NumberFormatException){
            }
        }







        //listener for blue value changes
        this.blueSliderVal.doOnTextChanged { text, _, _, _ ->
            try {
                if(text!=null) {
                    if(!dontSwitch.isChecked())
                    {
                        this.viewModel.saveInput(this.blueSliderVal.text.toString(),3)
                    }
                    val value = blueSliderVal.text.toString().toFloat()
                    if (value >= 0 && value <= 1) {
                        blueSlider.value = value //syncing slider value with the edit text value
                    }
                    val r = redSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                    val g = greenSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                    val b = blueSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                    val red = r.toInt()
                    val blue = b.toInt()
                    val green = g.toInt()
                    colorMaker.setBackgroundColor(Color.rgb(red, green, blue))
                }
            }catch(e:java.lang.NumberFormatException){
                 }
        }


        //listener when green slider moves
        greenSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Do nothing when the user starts touching the slider
            }

            override fun onStopTrackingTouch(slider: Slider) {
                val roundoff = df.format(greenSlider.value) // to limit the edit text values to two decimal places
                greenSliderVal.setText(roundoff.toString())  // syncing slider value with the edit text value
                val r = redSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val g = greenSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val b = blueSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val red = r.toInt()
                val blue = b.toInt()
                val green = g.toInt()
                colorMaker.setBackgroundColor(Color.rgb(red, green, blue)) // r,g,b values should be on 0-255 scale
            }
        })


        //listener when red slider moves
        redSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Do nothing when the user starts touching the slider
            }

            override fun onStopTrackingTouch(slider: Slider) {
                val roundoff = df.format(redSlider.value) // to limit the edit text values to two decimal places

                redSliderVal.setText(roundoff.toString())  // syncing slider value with the edit text value
                val r = redSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val g = greenSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val b = blueSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val red = r.toInt()
                val blue = b.toInt()
                val green = g.toInt()
                colorMaker.setBackgroundColor(Color.rgb(red, green, blue)) // r,g,b values should be on 0-255 scale
            }
        })


        //listener when blue slider moves
        blueSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Do nothing when the user starts touching the slider
            }

            override fun onStopTrackingTouch(slider: Slider) {
                val roundoff = df.format(blueSlider.value) // to limit the edit text values to two decimal places

                blueSliderVal.setText(roundoff.toString())  // syncing slider value with the edit text value
                val r = redSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val g = greenSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val b = blueSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val red = r.toInt()
                val blue = b.toInt()
                val green = g.toInt()
                colorMaker.setBackgroundColor(Color.rgb(red, green, blue)) // r,g,b values should be on 0-255 scale
            }
        })



        //if blue switch is checked / unchecked
        blueSwitch?.setOnCheckedChangeListener({ _ , isChecked ->
            if (isChecked){ blueSlider.isEnabled=true
                blueSliderVal.isEnabled=true
                if(!dontSwitch.isChecked()){
                    this.viewModel.saveInput(this.blueSwitch.isChecked().toString(),6)}
            }
            else {
                val z=0;
                blueSlider.isEnabled = false
                blueSlider.value=0.0F
                this.blueSliderVal.setText("")
                if(!dontSwitch.isChecked()){
                    this.viewModel.saveInput(this.blueSliderVal.text.toString(),3)
                this.viewModel.saveInput(this.blueSwitch.isChecked().toString(),6) }
                val r = redSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val g = greenSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val b = blueSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val red = r.toInt()
                val blue = b.toInt()
                val green = g.toInt()
                colorMaker.setBackgroundColor(Color.rgb(red, green, blue))
                blueSliderVal.isEnabled=false
            }
        })


        //if green switch is checked / unchecked
        greenSwitch?.setOnCheckedChangeListener({ _ , isChecked ->
            if (isChecked){
                greenSlider.isEnabled=true
                greenSliderVal.isEnabled=true
                if(!dontSwitch.isChecked()){
                    this.viewModel.saveInput(this.greenSwitch.isChecked().toString(),4)}

            }
            else {
                val z=0;
                greenSlider.isEnabled = false

                greenSlider.value=0.0F
                this.greenSliderVal.setText("")
                if(!dontSwitch.isChecked()){
                    this.viewModel.saveInput(this.greenSliderVal.text.toString(),1)
                this.viewModel.saveInput(this.greenSwitch.isChecked().toString(),4) }

                val r = redSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val g = greenSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val b = blueSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val red = r.toInt()
                val blue = b.toInt()
                val green = g.toInt()
                colorMaker.setBackgroundColor(Color.rgb(red, green, blue))
                greenSliderVal.isEnabled=false

            }
        })


        //if red switch is checked / unchecked
        redSwitch?.setOnCheckedChangeListener({ _ , isChecked ->
            if (isChecked) {
                redSlider.isEnabled = true
                redSliderVal.isEnabled=true
                if(!dontSwitch.isChecked()){
                    this.viewModel.saveInput(this.redSwitch.isChecked().toString(),5)}
            }
            else {
                val z=0;
                redSlider.isEnabled = false
                redSlider.value=0.0F
                this.redSliderVal.setText("")
                if(!dontSwitch.isChecked()){
                    this.viewModel.saveInput(this.redSliderVal.text.toString(),2)
                this.viewModel.saveInput(this.redSwitch.isChecked().toString(),5)}
                val r = redSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val g = greenSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val b = blueSlider.value * 255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
                val red = r.toInt()
                val blue = b.toInt()
                val green = g.toInt()
                colorMaker.setBackgroundColor(Color.rgb(red, green, blue))
                redSliderVal.isEnabled=false
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // save important variables to the bundle object
        outState.putString("greenSliderValText", greenSliderVal.text.toString())
        outState.putString("redSliderValText", redSliderVal.text.toString())
        outState.putString("blueSliderValText", blueSliderVal.text.toString())
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val gsval= savedInstanceState.getString("greenSliderValText")
        this.greenSliderVal.setText(gsval)
        val bsval= savedInstanceState.getString("blueSliderValText")
        this.blueSliderVal.setText(bsval)
        val rsval= savedInstanceState.getString("redSliderValText")
        this.redSliderVal.setText(rsval)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}