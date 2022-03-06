package ru.kudesnik.infograce

import android.content.SharedPreferences
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kudesnik.infograce.databinding.FragmentLayerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LAYER_SETTINGS = "layer_settings"
private const val SWITCH_CHECKED = "switch_checked"
private const val SLIDER_VALUE = "SLIDER_VALUE"
//private const val

open class LayerFragment : Fragment() {
    private var _binding: FragmentLayerBinding? = null
    private val binding get() = _binding!!
    private var adapter: LayerFragmentAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val valuesString = listOf<String>(
            "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2"
        )


//        val adapter:ListAdapter =
//            ArrayAdapter(requireContext(), R.layout.item_layer, R.id.itemName, valuesString)
        // use your custom layout


//        val navigationView = findViewById<View>(R.id.navigationView) as NavigationView
// Раздуть header view во время выполнения
// Раздуть header view во время выполнения
//        val headerLayout: View = navigationView.inflateHeaderView(R.layout.header_navigation_drawer)
// Теперь, при необходимости, мы можем найти элементы внутри
// header'а

//        val ivHeaderPhoto: ImageView = headerLayout.findViewById(R.id.imageView)

        with(binding) {
//            val cardView = included.baseCardView
//            val arrow = included.arrowButton
//            val hiddenView = included.hiddenView
//            val listView2 = listView
            val recyclerView: RecyclerView = recyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = LayerFragmentAdapter(valuesString)

//            listView2.setBackgroundColor(resources.getColor(R.color.purple_200))
//            listView.adapter = adapter



//            arrow.setOnClickListener {
//
//                Toast.makeText(requireActivity(), "ToastInFragment", Toast.LENGTH_SHORT).show()
//                if (hiddenView.visibility == View.VISIBLE) {
//                    TransitionManager.beginDelayedTransition(cardView, AutoTransition())
//                    hiddenView.visibility = View.GONE
//                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
//                } else {
//                    TransitionManager.beginDelayedTransition(
//                        cardView, AutoTransition()
//                    )
//                    hiddenView.visibility = View.VISIBLE
//                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
//                }
//            }
////Slider
//            val slider = included.slider
//            slider.value = getCurrentSlider().toFloat()
//            included.textViewItemPercent.text = slider.value.toInt().toString()
//            val textItemPercent = included.textViewItemPercent
//            slider.addOnChangeListener { slider, value, fromUser ->
//                setCurrentSlider(value.toInt())
//                textItemPercent.text = value.toInt().toString()
//            }
////Switch
//            val switch = included.switchItem
//            included.switchItem.isChecked = getCurrentSwitch()
//
//            switch.setOnCheckedChangeListener { buttonView, isChecked ->
//                val check = if (isChecked) "включен" else "выключен"
//
//                Toast.makeText(requireActivity(), "Переключатель $check", Toast.LENGTH_SHORT).show()
//                setCurrentSwitch(isChecked)
//            }
////Buttons
//            val button1 = included.itemButton1
//            button1.setOnClickListener {
//                Toast.makeText(requireContext(), "Нажата кнопка 1", Toast.LENGTH_SHORT).show()
//            }
//            val button2 = included.itemButton2
//            button2.setOnClickListener {
//                Toast.makeText(requireContext(), "Нажата кнопка 2", Toast.LENGTH_SHORT).show()
//            }
//            val button3 = included.itemButton3
//            button3.setOnClickListener {
//                Toast.makeText(requireContext(), "Нажата кнопка 3", Toast.LENGTH_SHORT).show()
//            }
        }
    }


    private fun setCurrentSlider(sliderValue: Int) {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
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
    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i element") }
        return data
    }

}