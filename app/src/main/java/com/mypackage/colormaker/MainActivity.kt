//Name: Pradhatri Vemula
//CWID: 885211540
//Email:pradhatrivemula@csu.fullerton.edu

package com.mypackage.colormaker
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.google.android.material.slider.Slider
import java.math.RoundingMode
import java.text.DecimalFormat
import android.text.Editable
import android.text.TextWatcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //object repository
        val button = findViewById<Button>(R.id.button)
        val greenSliderVal= findViewById(R.id.greenSliderVal) as EditText
        val redSliderVal= findViewById(R.id.redSliderVal) as EditText
        val blueSliderVal= findViewById<Button>(R.id.blueSliderVal) as EditText
        val greenSlider= findViewById<Button>(R.id.greenSlider) as Slider
        val redSlider= findViewById<Button>(R.id.redSlider) as Slider
        val blueSlider= findViewById<Button>(R.id.blueSlider) as Slider
        val blueSwitch= findViewById(R.id.blueSwitch) as SwitchCompat
        val redSwitch= findViewById(R.id.redSwitch) as SwitchCompat
        val greenSwitch= findViewById(R.id.greenSwitch) as SwitchCompat
        val colorMaker= findViewById(R.id.colorMaker) as View
        val df = DecimalFormat("#.##") // to limit the edit text values to two decimal places


        // reset all values to zero
        button?.setOnClickListener()
        {
            val z=0; // just a random variable to initialise textbox values
            greenSlider.value=0.0F
            redSlider.value=0.0F
            blueSlider.value=0.0F
            if(blueSlider.isEnabled == false) blueSlider.isEnabled=true
            if(redSlider.isEnabled == false) redSlider.isEnabled=true
            if(greenSlider.isEnabled == false) greenSlider.isEnabled=true
            if(blueSwitch.isChecked == false) blueSwitch.isChecked=true
            if(redSwitch.isChecked == false) redSwitch.isChecked=true
            if(greenSwitch.isChecked == false) greenSwitch.isChecked=true
            if(greenSliderVal.isEnabled==false) greenSliderVal.isEnabled=true
            if(blueSliderVal.isEnabled==false) blueSliderVal.isEnabled=true
            if(redSliderVal.isEnabled==false) redSliderVal.isEnabled=true
            greenSliderVal.setText(z.toString());
            blueSliderVal.setText(z.toString());
            redSliderVal.setText(z.toString());
            colorMaker.setBackgroundColor(Color.rgb(0, 0, 0)) // resetting r,g,b values to 0,0,0
        }

        //if green text value is changed
        greenSliderVal.doOnTextChanged { text, start, before, count ->
           try {
               val value = greenSliderVal.text.toString().toFloat()
               if(value>=0 &&value <=1){
                   greenSlider.value=value //syncing slider value with the edit text value
               }
           }catch(e:java.lang.NumberFormatException){
               // displays a toast message when values entered are greater than 1
               Toast.makeText(this@MainActivity,"Enter a value between 0 and 1", Toast.LENGTH_LONG).show()
           }
        }

        //if blue text value is changed
        blueSliderVal.doOnTextChanged { text, start, before, count ->
            try {
                val value = blueSliderVal.text.toString().toFloat()
                if(value>=0 &&value <=1){
                    blueSlider.value=value //syncing slider value with the edit text value
                }
            }catch(e:java.lang.NumberFormatException){
                // displays a toast message when values entered are greater than 1
                Toast.makeText(this@MainActivity,"Enter a value between 0 and 1", Toast.LENGTH_LONG).show()
            }
        }

        //if red edit text value is changed
        redSliderVal.doOnTextChanged { text, start, before, count ->
            try {
                val value = redSliderVal.text.toString().toFloat()
                if(value>=0 &&value <=1){
                    redSlider.value=value //syncing slider value with the edit text value
                }
            }catch(e:java.lang.NumberFormatException){
                // displays a toast message when values entered are greater than 1
                Toast.makeText(this@MainActivity,"Enter a value between 0 and 1", Toast.LENGTH_LONG).show()
            }
        }

        //if green slider is moved
        greenSlider.addOnChangeListener { slider, value, fromUser ->
            val roundoff = df.format(value) // to limit the edit text values to two decimal places
            greenSliderVal.setText(roundoff.toString())  // syncing slider value with the edit text value
            val r =redSlider.value*255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
            val g = greenSlider.value*255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
            val b = blueSlider.value*255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
            val red=r.toInt()
            val blue=b.toInt()
            val green=g.toInt()
            colorMaker.setBackgroundColor(Color.rgb(red, green, blue)) //r,g,b values should be on 0-255 scale
        }

        //if red slider is moved
        redSlider.addOnChangeListener { slider, value, fromUser ->
            val roundoff = df.format(value) // to limit the edit text values to two decimal places
            redSliderVal.setText(roundoff.toString())  // syncing slider value with the edit text value
            val r =redSlider.value*255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
            val g = greenSlider.value*255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
            val b = blueSlider.value*255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
            val red=r.toInt()
            val blue=b.toInt()
            val green=g.toInt()
            colorMaker.setBackgroundColor(Color.rgb(red, green, blue)) //r,g,b values should be on 0-255 scale
        }

        //if blue slider is moved
        blueSlider.addOnChangeListener { slider, value, fromUser ->
            val roundoff = df.format(value) // to limit the edit text values to two decimal places
            blueSliderVal.setText(roundoff.toString()) // syncing slider value with the edit text value
            val r =redSlider.value*255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
            val g = greenSlider.value*255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
            val b = blueSlider.value*255 // multiplying with 255 will convert the value from 0-1 scale to 0-255 scale
            val red=r.toInt()
            val blue=b.toInt()
            val green=g.toInt()
            colorMaker.setBackgroundColor(Color.rgb(red, green, blue)) //r,g,b values should be on 0-255 scale
        }

        //if blue switch is checked / unchecked
        blueSwitch?.setOnCheckedChangeListener({ _ , isChecked ->
            if (isChecked) blueSlider.isEnabled=true
            else {
                val z=0;
                blueSlider.isEnabled = false
                blueSlider.value=0.0F
                blueSliderVal.setText(z.toString())
                blueSliderVal.isEnabled=false
            }
        })

        //if green switch is checked / unchecked
        greenSwitch?.setOnCheckedChangeListener({ _ , isChecked ->
            if (isChecked) greenSlider.isEnabled=true
            else {
                val z=0;
                greenSlider.isEnabled = false

                greenSlider.value=0.0F
                greenSliderVal.setText(z.toString())
                greenSliderVal.isEnabled=false

            }
        })

        //if red switch is checked / unchecked
        redSwitch?.setOnCheckedChangeListener({ _ , isChecked ->
            if (isChecked) {
                redSlider.isEnabled = true
            }
            else {
                val z=0;
                redSlider.isEnabled = false
                redSlider.value=0.0F
                redSliderVal.setText(z.toString())
                redSliderVal.isEnabled=false
            }
        })

    }

}
