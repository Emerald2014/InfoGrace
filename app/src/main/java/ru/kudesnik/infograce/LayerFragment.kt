package ru.kudesnik.infograce

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.kudesnik.infograce.databinding.FragmentLayerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kudesnik.infograce.model.AppState
import ru.kudesnik.infograce.model.entities.Item
import ru.kudesnik.infograce.repository.RepositoryImpl


/*Задачи

1+. Перетаскивание элементов списка
2+. Сортировка списка по позиции, а не по порядку
3+. Сохранение в SharedPreference позиции, и переменных
4. Разобраться почему фрагменты не полностью подменяют друг друга, и при открытии выезжающей части начинают глючить
5. Нижнее меню
6. Разобраться почему нижнее меню не привязано к низу экрана
7. Добавить вью модель для оперативного обновления данных во вью*/


open class LayerFragment : Fragment() {
    private val viewModel: LayerViewModel by viewModel()

    private var _binding: FragmentLayerBinding? = null
    private val binding get() = _binding!!
    private val adapterLayer: LayerFragmentAdapter by lazy {
        LayerFragmentAdapter(requireContext(), object :SetSliderValue{
            override fun setSliderValue(item:Item) {
                viewModel.update(item = item)
            }
        } )
    }
    private var recyclerViewVer2: RecyclerView? = null
    private val repository = RepositoryImpl()
    lateinit var items2: List<Item>
     var itemsDao: List<Item> = listOf()

//    val items: List<Item> = listOf(
//        Item(0, "Слой делян", 2, false, 0),
//        Item(
//            1,
//            "Сигналы о лесоизменениях, тестовая выборка с ув-ным шагом",
//            1,
//            false,
//            1
//        ),
//        Item(2, "Преграды для прохождения огня", 0, false, 2),
//    )
    /*
    Item(3, "Маска облачности от 02.07.2021", 3, getCurrentSwitch(3), getCurrentSlider(3)),
    Item(4, "Маска облачности от 02.07.2021", 4, getCurrentSwitch(4), getCurrentSlider(4)),
    Item(5, "Папка со слоями", 5, getCurrentSwitch(5), getCurrentSlider(5)),
    Item(
        6,
        "Сигналы о лесоизменениях, тестовая выборка с ув-ным шагом",
        6,
        getCurrentSwitch(6),
        getCurrentSlider(6)
    ),
    Item(7, "Преграды для прохождения огня", 7, getCurrentSwitch(7), getCurrentSlider(7)),
    Item(8, "Контуры гарей", 8, getCurrentSwitch(8), getCurrentSlider(7)),
    Item(9, "Маска облачности от 02.07.2021", 9, getCurrentSwitch(9), getCurrentSlider(9)),
    Item(
        10,
        "Маска облачности от 02.07.2021",
        10,
        getCurrentSwitch(10),
        getCurrentSlider(10)
    ),
    Item(
        11,
        "Маска облачности от 02.07.2021",
        11,
        getCurrentSwitch(11),
        getCurrentSlider(11)
    ),
    Item(
        12,
        "Маска облачности от 02.07.2021",
        12,
        getCurrentSwitch(12),
        getCurrentSlider(12)
    ),
)*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        items2 = repository.getItems(requireContext())
Thread {
    for (item in items2) {
        repository.insertItem(item)

    }
    Log.d("myTag", repository.getAllItems().toString())
}.start()
        Thread{

             itemsDao =repository.getAllItems()
        }
        Log.d("myTag", "itemsDao - ${itemsDao.toString()}")

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
//            viewModel.getItems(requireContext())
            viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
            val callback: ItemTouchHelper.Callback = ItemMoveCallback(adapterLayer)
            val touchHelper = ItemTouchHelper(callback)
            touchHelper.attachToRecyclerView(recyclerViewVer2)
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
//Slider общий
//            var isCheckedInt = 0
//            for (item in items) {
//                if (item.isCheckedSwitch) isCheckedInt++
//            }
//            var isChecked = when (isCheckedInt) {
//                items.size -> 2
//                0 -> 0
//                else -> 1
//            }

//            sliderAll.value = isChecked.toFloat()
//            sliderAll.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
//                @SuppressLint("RestrictedApi")
//                override fun onStartTrackingTouch(slider: Slider) {
//                    TODO("Not yet implemented")
//                }
//
//                @SuppressLint("RestrictedApi")
//                override fun onStopTrackingTouch(slider: Slider) {
//                    when (slider.value) {
////                        0 ->  isChecked = false
////                        2 ->  isChecked = true
//                    }
//                }
//
//            })




//            setupClicks()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                adapterLayer.apply {
                    setItems(appState.modelData)

                }
                recyclerViewLayer.adapter = adapterLayer
            }
            is AppState.Error -> {}
            AppState.Loading -> {}

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
interface SetSliderValue {
    fun setSliderValue(item:Item)
}

    companion object {

        fun newInstance() = LayerFragment()

    }


}

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}