package ru.kudesnik.infograce.ui.layer_fragment

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import ru.kudesnik.infograce.R
import ru.kudesnik.infograce.databinding.FragmentLayerBinding
import ru.kudesnik.infograce.model.AppState
import ru.kudesnik.infograce.model.entities.Item
import ru.kudesnik.infograce.repository.RepositoryImpl
import ru.kudesnik.infograce.ui.utils.ItemMoveCallback


/*Задачи

1+. Перетаскивание элементов списка
2+. Сортировка списка по позиции, а не по порядку
3+. Сохранение в SharedPreference позиции, и переменных
4. Разобраться почему фрагменты при использовании шторки не полностью подменяют друг друга, и при открытии выезжающей части начинают глючить
5+. Нижнее меню
6. Разобраться почему нижнее меню не привязано к низу экрана в шторке
7+. Добавить вью модель для оперативного обновления данных во вью
8. При нажатии в нижнем меню кнопки перетаскивания менять значки на всех элементах
9. Функции главного слайдера по изменению состояние всех элементов и сохранению их значения для возврата
10. Добавление элемента в базу данных по кнопке
*/


open class LayerFragment : Fragment() {
    private val viewModel: LayerViewModel by viewModel()

    private var _binding: FragmentLayerBinding? = null
    private val binding get() = _binding!!
    private var isMovingMode = false
    private var adapterLayer: LayerFragmentAdapter? = null

    //    by lazy {
//        LayerFragmentAdapter(requireContext(), object : DoUpdate {
//            override fun doUpdateItem(item: Item) {
//                viewModel.update(item = item)
//            }
//        }, object : DoUpdateSlider {
//            override fun doUpdateSlider() {
//                getMainSliderPosition()
//            }
//
//        }, isMovingMode)
//    }
    private var recyclerViewVer2: RecyclerView? = null
    private val repository = RepositoryImpl()
    lateinit var items2: List<Item>
    var itemsDao: List<Item> = listOf()
    var mainSliderPosition: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkFirstLaunch()
        _binding = FragmentLayerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        items2 = repository.getItems(requireContext())
//Thread{
//
//    items2 = repository.getAllItems()
//}
//        Thread {
//            for (item in items2) {
//                repository.insertItem(item)
//
//            }
//            Log.d("myTag", repository.getAllItems().toString())
//        }.start()
//        Thread {
//
//            itemsDao = repository.getAllItems()
//        }
//        Log.d("myTag", "itemsDao - ${itemsDao.toString()}")

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
//Вот так все работает без ViewModel
//            recyclerViewVer2 = recyclerViewLayer
//            recyclerViewLayer.adapter = adapterLayer
//            Thread {
//                adapterLayer = LayerFragmentAdapter(requireContext()).apply { setItems(repository.getAllItems()) }
//            }.start()
//            recyclerViewLayer.adapter = adapterLayer
//            val callback: ItemTouchHelper.Callback = ItemMoveCallback(adapterLayer)
//            val touchHelper = ItemTouchHelper(callback)
//            touchHelper.attachToRecyclerView(recyclerViewVer2)
//Конец кода
//А так работает с ViewModel
            recyclerViewVer2 = recyclerViewLayer
            recyclerViewLayer.adapter = adapterLayer
            viewModel.getAllItems()
            viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
            val callback: ItemTouchHelper.Callback = ItemMoveCallback(adapterLayer)
            val touchHelper = ItemTouchHelper(callback)
            if (isMovingMode) touchHelper.attachToRecyclerView(recyclerViewVer2)
//Конец кода
//            recyclerViewVer2!!.adapter = adapterLayer


//            adapterLayer = LayerFragmentAdapter(items, requireContext())


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
//Slider общий, трехпозиционный

//            getMainSliderPosition()

//            uiScope.launch {
//                withContext(Dispatchers.IO) {
//                    allItems = repository.getAllItems()
//                    withContext(Dispatchers.Main) {
//                        for (item in allItems) {
//                            if (item.isCheckedSwitch) isCheckedInt++
//                        }
//                      var  test = when (isCheckedInt) {
//                            allItems.size -> 2
//                            0 -> 0
//                            else -> 1
//                        }
//                    }
//                }


//            viewModel.getMainSliderPosition()
//            viewModel.getMyLiveData().observe(viewLifecycleOwner) { renderData(it) }

//            viewModel.itemLiveData
//
//            sliderAll.value = viewModel.getMainSliderPosition().toFloat()
            getMainSliderPosition()

            Log.d("myTag", "Value - ${sliderAll.value.toString()}")
            sliderAll.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                @SuppressLint("RestrictedApi")
                override fun onStartTrackingTouch(slider: Slider) {
                    TODO("Not yet implemented")
                }

                @SuppressLint("RestrictedApi")
                override fun onStopTrackingTouch(slider: Slider) {
                    when (slider.value.toInt()) {
//                        0 ->  isChecked = false
//                        2 ->  isChecked = true
                    }
                }
            })
//Button Remove All
            buttonDrag.setOnClickListener {
                if (isMovingMode) {
                    llButtonDrag.setBackgroundColor(Color.parseColor("#4D566F"))

                    isMovingMode = false
                    viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
                } else {
                    llButtonDrag.setBackgroundColor(Color.parseColor("#59BD87"))
                    isMovingMode = true
                    viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
                }
            }
        }
    }


    private fun getMainSliderPosition() = with(binding) {

        var isCheckedInt = 0
        var test: Int
        var allItems: List<Item>

        Thread {
            allItems = repository.getAllItems()
            for (item in allItems) {
                if (item.isCheckedSwitch) isCheckedInt++
            }
            test = when (isCheckedInt) {
                allItems.size -> 2
                0 -> 0
                else -> 1
            }
            activity?.runOnUiThread {
                sliderAll.value = test.toFloat()
//                when(test) {
//                    0 -> {
//                        mainSwitch.setImageResource(R.drawable.ic_baseline_battery_0_bar_24)
//
//                    }
//                    1 -> {
//                        mainSwitch.setImageResource(R.drawable.ic_baseline_battery_4_bar_24)
//
//                    }
//                    2 -> {
//                        mainSwitch.setImageResource(R.drawable.ic_baseline_battery_full_24)
//
//                    }
//                }
                Log.d("myTag", "Тест в методе во фрагменте Test = $test")

            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                adapterLayer = LayerFragmentAdapter(requireContext(), object : DoUpdate {
                    override fun doUpdateItem(item: Item) {
                        viewModel.update(item = item)
                    }
                }, object : DoUpdateSlider {
                    override fun doUpdateSlider() {
                        getMainSliderPosition()
                    }

                }, isMovingMode)


                adapterLayer?.apply {
                    setItems(appState.modelData)

                }
                recyclerViewLayer.adapter = adapterLayer
            }
            is AppState.Error -> {}
            AppState.Loading -> {}
            is AppState.SuccessSlider -> {
//                viewModel.getMainSliderPosition()

                sliderAll.value = appState.sliderData.toFloat()
                Log.d("myTag", "Value Render - ${sliderAll.value.toString()}")

            }

        }
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


    interface DoUpdate {
        fun doUpdateItem(item: Item)
    }

    interface DoUpdateSlider {
        fun doUpdateSlider()
    }

    private fun checkFirstLaunch() {
        if (activity != null) {
            val pref: SharedPreferences = requireActivity().getSharedPreferences(
                "myPreferences",
                AppCompatActivity.MODE_PRIVATE
            )
            val isFirstLaunch = pref.getBoolean("first_usage", true)
            if (isFirstLaunch) {
                Thread {
                    for (item in repository.getItems(requireContext())) {
                        Log.d("myTag", item.toString())
                        repository.insertItem(item)
                    }
                }.start()
                val editor: SharedPreferences.Editor = pref.edit()
                editor.putBoolean("first_usage", false)
                editor.commit()
            }
        }
    }

    companion object {

        fun newInstance() = LayerFragment()

    }


}

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}