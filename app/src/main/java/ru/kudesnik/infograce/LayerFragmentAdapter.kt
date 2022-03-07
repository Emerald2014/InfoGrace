package ru.kudesnik.infograce

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kudesnik.infograce.databinding.ItemLayerBinding
import java.util.*


const val LAYER_SETTINGS = "layer_settings"
private const val SWITCH_CHECKED = "switch_checked"
const val SLIDER_VALUE_0 = "SLIDER_VALUE_0"
const val SLIDER_VALUE_1 = "SLIDER_VALUE_1"
const val SLIDER_VALUE_2 = "SLIDER_VALUE_2"

const val SWITCH_VALUE_0 = "SWITCH_VALUE_0"
const val SWITCH_VALUE_1 = "SWITCH_VALUE_1"
const val SWITCH_VALUE_2 = "SWITCH_VALUE_2"

class LayerFragmentAdapter(private val items: List<Item>, private val context: Context) :
    RecyclerView.Adapter<LayerFragmentAdapter.MyViewHolder>() {
    private lateinit var binding: ItemLayerBinding

    //    private lateinit var context:Context
    private var sliderConst = ""
    private var switchConst = ""

    var itemList = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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

        holder.bind(items.sortedBy { it.position }[position], context = context)

        setClickListener(holder);
    }

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

    override fun getItemCount(): Int = items.size


    private fun setCurrentSlider(sliderValue: Int, position: Int) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            LAYER_SETTINGS, AppCompatActivity.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        when (position) {
            0 -> sliderConst = SLIDER_VALUE_0
            1 -> sliderConst = SLIDER_VALUE_1
            2 -> sliderConst = SLIDER_VALUE_2
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
         }
        editor.putBoolean(switchConst, switchIsChecked)
        editor.apply()
    }



//    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
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
//    override fun onItemDismiss(position: Int) {
//        items2.remove(position);
//        notifyItemRemoved(position);
//    }
}


