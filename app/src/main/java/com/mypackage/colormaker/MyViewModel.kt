package com.mypackage.colormaker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


const val INITIAL_COUNTER_VALUE = ""

class MyViewModel : ViewModel() {

    private val prefs = PreferencesDataStore.getRepository()

    fun saveInput(s: String, index: Int) {

        viewModelScope.launch {
            prefs.saveInput(s, index)
            Log.d("MainActivity", "saved input to datastore")
        }
    }

    fun getRepository(): PreferencesDataStore {
        return prefs
    }

    fun loadInputs(act: MainActivity) {

        viewModelScope.launch {
            prefs.greenSliderVal.collectLatest {

                act.greenSliderVal.setText(it.toString())
            }

            Log.d("MainActivity", "Done collecting greensliderval")
        }

        viewModelScope.launch {
            prefs.redSliderVal.collectLatest {
                act.redSliderVal.setText(it.toString())
                Log.d("MainActivity", "Done collecting redsliderval")
            }
        }
        viewModelScope.launch {
            prefs.blueSliderVal.collectLatest {
                act.blueSliderVal.setText(it.toString())
                Log.d("MainActivity", "Done collecting bluesliderval")
            }
        }
        viewModelScope.launch {
            prefs.greenSwitch.collectLatest {
                act.greenSwitch.setChecked(it.toBoolean())
                Log.d("MainActivity", "Done collecting green switch state")
            }
        }
        viewModelScope.launch {
            prefs.redSwitch.collectLatest {
                act.redSwitch.setChecked(it.toBoolean())
                Log.d("MainActivity", "Done collecting red switch state")
            }
        }
        viewModelScope.launch {
            prefs.blueSwitch.collectLatest {
                act.blueSwitch.setChecked(it.toBoolean())
                //act.blueSwitch.setText(it.toString())
                Log.d("MainActivity", "Done collecting blue switch state")
            }
        }
    }



    public override fun onCleared() {
        super.onCleared()
        Log.d("MainActivity", "OnCleared of CounterViewActivity called")
    }
}
