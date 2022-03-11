package ru.kudesnik.infograce.usecases

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import ru.kudesnik.infograce.model.*

class UseCaseGetSharedPref {
    fun getCurrentSlider(position: Int, context: Context): Int {
        var sliderConst = ""
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        when (position) {
            0 -> sliderConst = SLIDER_VALUE_0
            1 -> sliderConst = SLIDER_VALUE_1
            2 -> sliderConst = SLIDER_VALUE_2
            3 -> sliderConst = SLIDER_VALUE_3
            4 -> sliderConst = SLIDER_VALUE_4
            5 -> sliderConst = SLIDER_VALUE_5
            6 -> sliderConst = SLIDER_VALUE_6
            7 -> sliderConst = SLIDER_VALUE_7
            8 -> sliderConst = SLIDER_VALUE_8
            9 -> sliderConst = SLIDER_VALUE_9
            10 -> sliderConst = SLIDER_VALUE_10
            11 -> sliderConst = SLIDER_VALUE_11
            12 -> sliderConst = SLIDER_VALUE_12
        }
        return (sharedPreferences.getInt(sliderConst, 0))
    }

    fun getCurrentSwitch(position: Int, context: Context): Boolean {
        var switchConst = ""
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        when (position) {
            0 -> switchConst = SWITCH_VALUE_0
            1 -> switchConst = SWITCH_VALUE_1
            2 -> switchConst = SWITCH_VALUE_2
            3 -> switchConst = SWITCH_VALUE_3
            4 -> switchConst = SWITCH_VALUE_4
            5 -> switchConst = SWITCH_VALUE_5
            6 -> switchConst = SWITCH_VALUE_6
            7 -> switchConst = SWITCH_VALUE_7
            8 -> switchConst = SWITCH_VALUE_8
            9 -> switchConst = SWITCH_VALUE_9
            10 -> switchConst = SWITCH_VALUE_10
            11 -> switchConst = SWITCH_VALUE_11
            12 -> switchConst = SWITCH_VALUE_12
        }
        return (sharedPreferences.getBoolean(switchConst, false))
    }

    fun getCurrentPosition(position: Int, context: Context): Int {
        var positionConst = ""
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        when (position) {
            0 -> positionConst = SLIDER_POSITION_0
            1 -> positionConst = SLIDER_POSITION_1
            2 -> positionConst = SLIDER_POSITION_2
            3 -> positionConst = SLIDER_POSITION_3
            4 -> positionConst = SLIDER_POSITION_4
            5 -> positionConst = SLIDER_POSITION_5
            6 -> positionConst = SLIDER_POSITION_6
            7 -> positionConst = SLIDER_POSITION_7
            8 -> positionConst = SLIDER_POSITION_8
            9 -> positionConst = SLIDER_POSITION_9
            10 -> positionConst = SLIDER_POSITION_10
            11 -> positionConst = SLIDER_POSITION_11
            12 -> positionConst = SLIDER_POSITION_12
        }
        return (sharedPreferences.getInt(positionConst, 0))
    }


}