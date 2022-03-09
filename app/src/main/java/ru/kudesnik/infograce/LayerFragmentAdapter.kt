package ru.kudesnik.infograce

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kudesnik.infograce.databinding.ItemLayerBinding
import java.util.*
import kotlin.collections.ArrayList


const val LAYER_SETTINGS = "layer_settings"
const val SLIDER_VALUE_0 = "SLIDER_VALUE_0"
const val SLIDER_VALUE_1 = "SLIDER_VALUE_1"
const val SLIDER_VALUE_2 = "SLIDER_VALUE_2"
const val SLIDER_VALUE_3 = "SLIDER_VALUE_3"
const val SLIDER_VALUE_4 = "SLIDER_VALUE_4"
const val SLIDER_VALUE_5 = "SLIDER_VALUE_5"
const val SLIDER_VALUE_6 = "SLIDER_VALUE_6"
const val SLIDER_VALUE_7 = "SLIDER_VALUE_7"
const val SLIDER_VALUE_8 = "SLIDER_VALUE_8"
const val SLIDER_VALUE_9 = "SLIDER_VALUE_9"
const val SLIDER_VALUE_10 = "SLIDER_VALUE_10"
const val SLIDER_VALUE_11 = "SLIDER_VALUE_11"
const val SLIDER_VALUE_12 = "SLIDER_VALUE_12"

const val SWITCH_VALUE_0 = "SWITCH_VALUE_0"
const val SWITCH_VALUE_1 = "SWITCH_VALUE_1"
const val SWITCH_VALUE_2 = "SWITCH_VALUE_2"
const val SWITCH_VALUE_3 = "SWITCH_VALUE_3"
const val SWITCH_VALUE_4 = "SWITCH_VALUE_4"
const val SWITCH_VALUE_5 = "SWITCH_VALUE_5"
const val SWITCH_VALUE_6 = "SWITCH_VALUE_6"
const val SWITCH_VALUE_7 = "SWITCH_VALUE_7"
const val SWITCH_VALUE_8 = "SWITCH_VALUE_8"
const val SWITCH_VALUE_9 = "SWITCH_VALUE_9"
const val SWITCH_VALUE_10 = "SWITCH_VALUE_10"
const val SWITCH_VALUE_11 = "SWITCH_VALUE_11"
const val SWITCH_VALUE_12 = "SWITCH_VALUE_12"

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
            imageItem.load(R.drawable.item_temp)

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
                    hiddenView.visibility = View.GONE
                    arrowButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                } else {
                    TransitionManager.beginDelayedTransition(
                        baseCardView, AutoTransition()
                    )
                    itemName.setTypeface(null, Typeface.BOLD)
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
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(itemList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }


}


