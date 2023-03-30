package com.mypackage.colormaker


import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.preferencesDataStoreFile;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.distinctUntilChanged;
import kotlinx.coroutines.flow.map;
import androidx.datastore.preferences.core.*


class PreferencesDataStore private constructor(private val dataStore: DataStore<Preferences>) {


    val greenSliderVal: Flow<String> = this.dataStore.data.map { prefs ->
        prefs[gs_KEY] ?: INITIAL_COUNTER_VALUE
    }.distinctUntilChanged()
    val blueSliderVal: Flow<String> = this.dataStore.data.map { prefs ->
        prefs[bs_KEY] ?: INITIAL_COUNTER_VALUE
    }.distinctUntilChanged()
    val redSliderVal: Flow<String> = this.dataStore.data.map { prefs ->
        prefs[rs_KEY] ?: INITIAL_COUNTER_VALUE
    }.distinctUntilChanged()
    val greenSwitch: Flow<String> = this.dataStore.data.map { prefs ->
        prefs[gsw_KEY] ?: INITIAL_COUNTER_VALUE
    }.distinctUntilChanged()
    val blueSwitch: Flow<String> = this.dataStore.data.map { prefs ->
        prefs[bsw_KEY] ?: INITIAL_COUNTER_VALUE
    }.distinctUntilChanged()
    val redSwitch: Flow<String> = this.dataStore.data.map { prefs ->
        prefs[rsw_KEY] ?: INITIAL_COUNTER_VALUE
    }.distinctUntilChanged()


    private suspend fun saveStringValue(key: Preferences.Key<String>, value: String) {
            this.dataStore.edit { prefs ->
                prefs[key] = value
            }
        }



    suspend fun saveInput(value:String, index: Int){
        val key: Preferences.Key<String> = when (index) {
            1 -> gs_KEY
            2 -> rs_KEY
            3 -> bs_KEY
            4 -> gsw_KEY
            5 -> rsw_KEY
            6 -> bsw_KEY
            else -> {
                throw NoSuchFieldException("invalid input for index: $index")
            }
        }
        this.saveStringValue(key,value)
    }

        companion object {
            private val gs_KEY = stringPreferencesKey("greenSliderVal")
            private val bs_KEY = stringPreferencesKey("blueSliderVal")
            private val rs_KEY = stringPreferencesKey("redSliderVal")
            private val gsw_KEY = stringPreferencesKey("greenSwitch")
            private val bsw_KEY = stringPreferencesKey("blueSwitch")
            private val rsw_KEY = stringPreferencesKey("redSwitch")

            private const val PREFERENCES_DATA_FILE_NAME = "settings"
            private var INSTANCE: PreferencesDataStore? = null


            fun initialize(context: Context) {
                if (INSTANCE == null) {
                    val dataStore = PreferenceDataStoreFactory.create {
                        context.preferencesDataStoreFile(PREFERENCES_DATA_FILE_NAME)
                    }
                    INSTANCE = PreferencesDataStore(dataStore)
                }
            }
            fun getRepository(): PreferencesDataStore {
                return INSTANCE ?: throw IllegalStateException("AppPreferencesRepository not initialized yet")

            }
        }
}