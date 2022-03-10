package ru.kudesnik.infograce

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kudesnik.infograce.databinding.ItemLayerBinding
import ru.kudesnik.infograce.model.Item
import ru.kudesnik.infograce.model.*

import java.util.*
import kotlin.collections.ArrayList





class LayerFragmentAdapter(
//    private val items: List<Item>,
    private val context: Context

) :
    RecyclerView.Adapter<LayerFragmentAdapter.MyViewHolder>(),
    ItemMoveCallback.ItemTouchHelperContract {
    private lateinit var binding: ItemLayerBinding
    private var itemList= ArrayList<Item>()
//    private var itemList: List<Item> = mutableListOf()
//    private val itemForMoved : ArrayList<Item> = itemList.add
private val itemTest = listOf("1", "2", "3")
    private var sliderConst = ""
    private var switchConst = ""

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(data: List<Item>) {
        itemList.addAll(data)
        notifyDataSetChanged()

    }
//    var itemList = mutableListOf<Item>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item, context: Context) = with(binding) {
            itemName.text = item.name
            val link: Int = item.image
            imageItem.load(link)

//Slider
            slider.value = item.sliderValue.toFloat()
            textViewItemPercent.text = slider.value.toInt().toString()
            slider.addOnChangeListener { slider, value, fromUser ->
                setCurrentSlider(value.toInt(), item.id)
                textViewItemPercent.text = value.toInt().toString()
            }
//Switch
            val switch = switchItem
            switchItem.isChecked = item.isCheckedSwitch

            switchItem.setOnCheckedChangeListener { buttonView, isChecked ->
                val check = if (isChecked) "включен" else "выключен"

                Toast.makeText(context, "Переключатель $check", Toast.LENGTH_SHORT).show()
                setCurrentSwitch(isChecked, item.id)
            }
//Buttons
            val button1 = itemButton1
            button1.setOnClickListener {
                Toast.makeText(context, "Нажата кнопка 1", Toast.LENGTH_SHORT).show()
            }
            val button2 = itemButton2
            button2.setOnClickListener {
                Toast.makeText(context, "Нажата кнопка 2", Toast.LENGTH_SHORT).show()
            }
            val button3 = itemButton3
            button3.setOnClickListener {
                Toast.makeText(context, "Нажата кнопка 3", Toast.LENGTH_SHORT).show()
            }

//Root нажатие
            root.setOnClickListener {

//                Toast.makeText(require(), "ToastInFragment", Toast.LENGTH_SHORT).show()
                if (hiddenView.visibility == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(baseCardView, AutoTransition())
                    itemName.setTypeface(null, Typeface.NORMAL)
                    itemName.setTextColor(context.resources.getColor(R.color.white))
                    imageItem.setColorFilter(context.resources.getColor(R.color.white))

                    hiddenView.visibility = View.GONE
                    arrowButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                } else {
                    TransitionManager.beginDelayedTransition(
                        baseCardView, AutoTransition()
                    )
                    itemName.setTypeface(null, Typeface.BOLD)
                    imageItem.setColorFilter(context.resources.getColor(R.color.item_text_bold))
                    itemName.setTextColor(context.resources.getColor(R.color.item_text_bold))
                    hiddenView.visibility = View.VISIBLE
                    arrowButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                }
            }

//Root LongClick
            root.setOnLongClickListener {
                Toast.makeText(context, "Long Click ", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemLayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layer, parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(itemList.sortedBy { it.position }[position], context = context)
//setCurrentPosition(position)
        setClickListener(holder);
//
//        holder.image.setOnTouchListener(OnTouchListener { v, event ->
//            if (event.actionMasked === MotionEvent.ACTION_DOWN) {
//                fragmentThis.onStartDrag(holder)
//            }
//            false
//        })

    }

//    fun onItemDismiss(position: Int) {
//       items.remove(position)
//        notifyItemRemoved(position)
//    }
//
//    fun onItemMove(fromPosition: Int, toPosition: Int) {
//        val prev: String = items.remove(fromPosition)
//        mItems.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, prev)
//        notifyItemMoved(fromPosition, toPosition)
//    }

    // Настраиваем мониторинг кликов
    private fun setClickListener(viewHolder: MyViewHolder) {
        if (mOnItemClickListener != null) {

            // Настраиваем слушателя через View's setOnClickListener
            viewHolder.itemView.setOnClickListener {
                mOnItemClickListener!!.onItemClick(
                    viewHolder.itemView,
                    viewHolder.layoutPosition
                )

            }
            viewHolder.itemView.setOnLongClickListener {
                mOnItemClickListener!!.onItemLongClick(
                    viewHolder.itemView,
                    viewHolder.layoutPosition
                )
                false
            }
        }
    }

    // интерфейс события щелчка по элементу, обратный вызов Activity
    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
        fun onItemLongClick(view: View?, position: Int)
    }

    private var mOnItemClickListener: OnItemClickListener? = null

    // Вызывается в Activity
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mOnItemClickListener = listener
    }

    override fun getItemCount(): Int = itemList.size


    private fun setCurrentSlider(sliderValue: Int, position: Int) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
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
        editor.putInt(sliderConst, sliderValue)
        editor.apply()
    }

    private fun setCurrentSwitch(switchIsChecked: Boolean, position: Int) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
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
        editor.putBoolean(switchConst, switchIsChecked)
        editor.apply()
    }

//    fun onItemMove(fromPosition: Int, toPosition: Int) {
//        val prev: Item = itemList.removeAt(fromPosition)
//        itemList.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, prev)
//        notifyItemMoved(fromPosition, toPosition)
//    }
//     fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
//        if (fromPosition < toPosition) {
//            for (i in fromPosition until toPosition) {
//                Collections.swap(items, i, i + 1)
//            }
//        } else {
//            for (i in fromPosition downTo toPosition + 1) {
//                Collections.swap(items, i, i - 1)
//            }
//        }
//        notifyItemMoved(fromPosition, toPosition)
//        return true
//    }

    //    val items2 = listOf<String>("1", "2")
//
//    fun onItemDismiss(position: Int) {
//        itemList.removeAt(position);
//        notifyItemRemoved(position);
//    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(itemList, i, i + 1)
            }
        } else {
//            setCurrentPositionUp(toPosition)

            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(itemList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    private fun setCurrentPositionUp(position: Int) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        var positionConst = ""
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        when (position) {
            0 -> {
                editor.putInt(SLIDER_POSITION_1, position)
                editor.putInt(SLIDER_POSITION_0, position+1)
            }
            1 -> {
                editor.putInt(SLIDER_POSITION_2, position)
                editor.putInt(SLIDER_POSITION_1, position+1)
            }
            2 -> {
                editor.putInt(SLIDER_POSITION_3, position)
                editor.putInt(SLIDER_POSITION_2, position+1)
            }
            3 -> {
                editor.putInt(SLIDER_POSITION_4, position)
                editor.putInt(SLIDER_POSITION_3, position+1)
            }
            4 -> {
                editor.putInt(SLIDER_POSITION_5, position)
                editor.putInt(SLIDER_POSITION_4, position+1)
            }
            5 -> {
                editor.putInt(SLIDER_POSITION_6, position)
                editor.putInt(SLIDER_POSITION_5, position+1)
            }
            6 -> {
                editor.putInt(SLIDER_POSITION_7, position)
                editor.putInt(SLIDER_POSITION_6, position+1)
            }
            7 -> {
                editor.putInt(SLIDER_POSITION_8, position)
                editor.putInt(SLIDER_POSITION_7, position+1)
            }
            8 -> {
                editor.putInt(SLIDER_POSITION_9, position)
                editor.putInt(SLIDER_POSITION_8, position+1)
            }
            9 -> {
                editor.putInt(SLIDER_POSITION_10, position)
                editor.putInt(SLIDER_POSITION_9, position+1)
            }
            10 -> {
                editor.putInt(SLIDER_POSITION_11, position)
                editor.putInt(SLIDER_POSITION_10, position+1)
            }
            11 -> {
                editor.putInt(SLIDER_POSITION_12, position)
                editor.putInt(SLIDER_POSITION_11, position+1)
            }
        }
        editor.putInt(positionConst, position)
        editor.apply()
    }

    private fun setCurrentPosition(position: Int) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        var positionConst = ""
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
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
            editor.putInt(positionConst, position)
                    editor.apply()

    }
}


