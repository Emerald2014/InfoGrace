package ru.kudesnik.infograce

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kudesnik.infograce.databinding.FragmentLayerBinding

//Задачи
/*1. Перетаскивание элементов списка
2. Сортировка списка по позиции, а не по порядку
3. Сохранение в SharedPreference позиции, и переменных*/
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
            "Слой", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2"
        )
        val items: List<Item> = listOf<Item>(
            Item(0, "Слой делян", 2, false, 65),
            Item(1, "Сигналы о лесоизменениях, тестовая выборка с ув-ным шагом", 1, true, 26),
            Item(2, "Преграды для прохождения огня", 0, true, 0),
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
//            val cardView = included.baseCardView
//            val arrow = included.arrowButton
//            val hiddenView = included.hiddenView
//            val listView2 = listView
            val rw: RecyclerView = recyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = LayerFragmentAdapter(items, requireContext())

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
            setupSwipeListener(rw)



            setupClicks()
        }
    }

    private fun setupClicks() =
        adapter?.setOnItemClickListener(object : LayerFragmentAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                Toast.makeText(requireContext(), "Click $position", Toast.LENGTH_SHORT).show()

            }

            override fun onItemLongClick(view: View?, position: Int) {
                Toast.makeText(requireContext(), "Long Click $position", Toast.LENGTH_SHORT).show()

            }
        })


    private fun setupSwipeListener(rw: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.UP
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter?.itemList?.get(viewHolder.adapterPosition)
                Toast.makeText(requireContext(), "Swipe $item", Toast.LENGTH_SHORT).show()
//                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rw)
    }
        fun getMovementFlags(
            recyclerView: RecyclerView?,
            viewHolder: RecyclerView.ViewHolder?
        ): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            return makeMovementFlags(dragFlags, swipeFlags)
        }

        fun isLongPressDragEnabled(): Boolean {
            return true
        }

        fun isItemViewSwipeEnabled(): Boolean {
            return true
        }

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