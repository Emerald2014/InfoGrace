package ru.kudesnik.infograce

import android.content.SharedPreferences
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.kudesnik.infograce.databinding.FragmentLayerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LAYER_SETTINGS = "layer_settings"
private const val SWITCH_CHECKED = "switch_checked"
private const val SLIDER_VALUE = "SLIDER_VALUE"
//private const val

class LayerFragment : Fragment() {
    private var _binding: FragmentLayerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val navigationView = findViewById<View>(R.id.navigationView) as NavigationView
// Раздуть header view во время выполнения
// Раздуть header view во время выполнения
//        val headerLayout: View = navigationView.inflateHeaderView(R.layout.header_navigation_drawer)
// Теперь, при необходимости, мы можем найти элементы внутри
// header'а

//        val ivHeaderPhoto: ImageView = headerLayout.findViewById(R.id.imageView)

        with(binding) {
            val cardView = baseCardview
            val arrow = arrowButton
            val hiddenView = hiddenView
            arrow.setOnClickListener {

                Toast.makeText(requireActivity(), "ToastInFragment", Toast.LENGTH_SHORT).show()
                if (hiddenView.visibility == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                    hiddenView.visibility = View.GONE
                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                } else {
                    TransitionManager.beginDelayedTransition(
                        cardView,
                        AutoTransition()
                    )
                    hiddenView.visibility = View.VISIBLE
                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                }
            }
//Slider
            val slider = slider
            slider.value = getCurrentSlider().toFloat()
            textViewItemPercent.text = slider.value.toInt().toString()
            val textItemPercent = textViewItemPercent
            slider.addOnChangeListener { slider, value, fromUser ->
                setCurrentSlider(value.toInt())
                textItemPercent.text = value.toInt().toString()
            }
//Switch
            val switch = switchItem
            switchItem.isChecked =         getCurrentSwitch()

            switch.setOnCheckedChangeListener { buttonView, isChecked ->
                val check = if (isChecked) {
                    "включен"

                } else {
                    "выключен"
                }
                Toast.makeText(requireActivity(), "Переключатель $check", Toast.LENGTH_SHORT).show()
                setCurrentSwitch(isChecked)
            }
        }
    }

    private fun setCurrentSlider(sliderValue: Int) {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(SLIDER_VALUE, sliderValue)
        editor.apply()
    }
    private fun getCurrentSlider(): Int {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        return (sharedPreferences.getInt(SLIDER_VALUE, 0))
    }

    private fun setCurrentSwitch(switchIsChecked: Boolean) {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(SWITCH_CHECKED, switchIsChecked)
        editor.apply()
    }

    private fun getCurrentSwitch(): Boolean {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        return (sharedPreferences.getBoolean(SWITCH_CHECKED, true))
    }

    companion object {

        fun newInstance() = LayerFragment()

    }
}