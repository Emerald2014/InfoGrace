package ru.kudesnik.infograce

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import ru.kudesnik.infograce.databinding.FragmentLayerBinding

//Задачи
/*1. Перетаскивание элементов списка
2+. Сортировка списка по позиции, а не по порядку
3+. Сохранение в SharedPreference позиции, и переменных
4. Разобраться почему фрагменты не полностью подменяют друг друга, и при открытии выезжающей части начинают глючить
5. Нижнее меню
6. Разобраться почему нижнее меню не привязано к низу экрана
7. Добавить вью модель для оперативного обновления данных во вью*/
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val LAYER_SETTINGS = "layer_settings"
private const val SWITCH_CHECKED = "switch_checked"
private const val SLIDER_VALUE = "SLIDER_VALUE"

//private const val

open class LayerFragment : Fragment() {
    private var _binding: FragmentLayerBinding? = null
    private val binding get() = _binding!!
    private var adapterLayer: LayerFragmentAdapter? = null
    private var adapterLayerTest: LayerFragmentAdapterTest? = null
    lateinit var mItemTouchHelper: ItemTouchHelper
    var recyclerViewVer2: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemTest = listOf(1, 2, 3, 4)

        val items: List<Item> = listOf<Item>(
            Item(0, "Слой делян", 2, getCurrentSwitch(0), getCurrentSlider(0)),
            Item(
                1,
                "Сигналы о лесоизменениях, тестовая выборка с ув-ным шагом",
                1,
                getCurrentSwitch(1),
                getCurrentSlider(1)
            ),
            Item(2, "Преграды для прохождения огня", 0, getCurrentSwitch(2), getCurrentSlider(2)),
            Item(2, "Преграды для прохождения огня", 0, getCurrentSwitch(2), getCurrentSlider(2)),
            Item(2, "Преграды для прохождения огня", 0, getCurrentSwitch(2), getCurrentSlider(2)),
            Item(2, "Преграды для прохождения огня", 0, getCurrentSwitch(2), getCurrentSlider(2)),
            Item(2, "Преграды для прохождения огня", 0, getCurrentSwitch(2), getCurrentSlider(2)),
            Item(2, "Преграды для прохождения огня", 0, getCurrentSwitch(2), getCurrentSlider(2)),
//            Item(3, "Маска облачности от 02.07.2021", 3),
//            Item(4, "Маска облачности от 02.07.2021", 4),
//            Item(5, "Папка со слоями", 5),
//            Item(6, "Сигналы о лесоизменениях, тестовая выборка с ув-ным шагом", 6),
//            Item(7, "Преграды для прохождения огня", 7),
//            Item(8, "Контуры гарей", 8),
//            Item(9, "Маска облачности от 02.07.2021", 9),
//            Item(10, "Маска облачности от 02.07.2021", 10),
//            Item(11, "Маска облачности от 02.07.2021", 1),
//            Item(12, "Маска облачности от 02.07.2021", 12),
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
recyclerViewVer2 = recyclerViewLayer
            recyclerViewLayer.layoutManager = LinearLayoutManager(requireContext())

            adapterLayer = LayerFragmentAdapter(items, requireContext())
            val callback: ItemTouchHelper.Callback = ItemMoveCallback(adapterLayer)
            val touchHelper = ItemTouchHelper(callback)
            touchHelper.attachToRecyclerView(recyclerViewVer2)
            recyclerViewVer2!!.adapter = adapterLayer

//            val cardView = included.baseCardView
//            val arrow = included.arrowButton
//            val hiddenView = included.hiddenView
//            val listView2 = listView

//            val rw: RecyclerView = recyclerViewLayer
//            recyclerViewLayer.adapter = LayerFragmentAdapter(items, requireContext())
//
//recyclerViewLayerTest.layoutManager = LinearLayoutManager(requireContext())
//            recyclerViewLayerTest.adapter = LayerFragmentAdapterTest(itemTest, requireContext())


//            adapterLayer = LayerFragmentAdapter(items, requireContext())

//            val rw: RecyclerView = recyclerView
//            val rwAdap = LayerFragmentAdapter(items, requireContext(), this@LayerFragment)


//            val callbackTest: ItemTouchHelper.Callback = ItemMoveCallback(adapterLayerTest)
//            val touchHelperTest = ItemTouchHelper(callbackTest)
//            touchHelperTest.attachToRecyclerView(recyclerViewLayerTest)


//            rw.adapter = rwAdap
//            recyclerViewLayer.adapter = adapterLayer
//            val rwAdapter = recyclerView.adapter
//            recyclerView.layoutManager = LinearLayoutManager(requireContext())


//            val callback = SimpleItemTouchHelperCallback(adapter)
//            mItemTouchHelper = ItemTouchHelper(callback)
//            mItemTouchHelper.attachToRecyclerView(rw)



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
//            setupSwipeListener(rw)
//Slider общий
            var isCheckedInt = 0
            for (item in items) {
                if (item.isCheckedSwitch) isCheckedInt++
            }
            var isChecked = when (isCheckedInt) {
                items.size -> 2
                0 -> 0
                else -> 1
            }

            sliderAll.value = isChecked.toFloat()
            sliderAll.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                @SuppressLint("RestrictedApi")
                override fun onStartTrackingTouch(slider: Slider) {
                    TODO("Not yet implemented")
                }

                @SuppressLint("RestrictedApi")
                override fun onStopTrackingTouch(slider: Slider) {
                    when (slider.value) {
//                        0 ->  isChecked = false
//                        2 ->  isChecked = true
                    }
                }

            })

            testClick.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    "Переключателей включено: $isChecked",
                    Toast.LENGTH_SHORT
                ).show()

            }


//            setupClicks()
        }
    }


    private fun getCurrentSlider(position: Int): Int {
        var sliderConst = ""
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        when (position) {
            0 -> sliderConst = SLIDER_VALUE_0
            1 -> sliderConst = SLIDER_VALUE_1
            2 -> sliderConst = SLIDER_VALUE_2
        }
        return (sharedPreferences.getInt(sliderConst, 0))
    }

    private fun getCurrentSwitch(position: Int): Boolean {
        var switchConst = ""
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        when (position) {
            0 -> switchConst = SWITCH_VALUE_0
            1 -> switchConst = SWITCH_VALUE_1
            2 -> switchConst = SWITCH_VALUE_2
        }
        return (sharedPreferences.getBoolean(switchConst, false))
    }

    //    private fun setupClicks() =
//        adapter?.setOnItemClickListener(object : LayerFragmentAdapter.OnItemClickListener {
//            override fun onItemClick(view: View?, position: Int) {
//                Toast.makeText(requireContext(), "Click $position", Toast.LENGTH_SHORT).show()
//
//            }
//
//            override fun onItemLongClick(view: View?, position: Int) {
//                Toast.makeText(requireContext(), "Long Click $position", Toast.LENGTH_SHORT).show()
//
//            }
//        })
//    open fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
//        if (viewHolder != null) {
//            mItemTouchHelper.startDrag(viewHolder)
//        }
//    }

//    private fun setupSwipeListener(rw: RecyclerView) {
//        val callback = object : ItemTouchHelper.SimpleCallback(
//            0,
//            ItemTouchHelper.LEFT or ItemTouchHelper.UP
//        ) {
//
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val item = adapter?.itemList?.get(viewHolder.adapterPosition)
//                Toast.makeText(requireContext(), "Swipe $item", Toast.LENGTH_SHORT).show()
////                viewModel.deleteShopItem(item)
//            }
//        }
//        val itemTouchHelper = ItemTouchHelper(callback)
//        itemTouchHelper.attachToRecyclerView(rw)
//    }

//    fun getMovementFlags(
//        recyclerView: RecyclerView?,
//        viewHolder: RecyclerView.ViewHolder?
//    ): Int {
//        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
//        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
//        return makeMovementFlags(dragFlags, swipeFlags)
//    }
//
//    fun isLongPressDragEnabled(): Boolean {
//        return true
//    }
//
//    fun isItemViewSwipeEnabled(): Boolean {
//        return true
//    }

    fun setCurrentSlider(sliderValue: Int) {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(SLIDER_VALUE, sliderValue)
        editor.apply()
    }

    fun getCurrentSlider(): Int {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        return (sharedPreferences.getInt(SLIDER_VALUE, 0))
    }

    fun setCurrentSwitch(switchIsChecked: Boolean) {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(SWITCH_CHECKED, switchIsChecked)
        editor.apply()
    }

    fun getCurrentSwitch(): Boolean {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        return (sharedPreferences.getBoolean(SWITCH_CHECKED, true))
    }

    companion object {

        fun newInstance() = LayerFragment()

    }


}

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}