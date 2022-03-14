package ru.kudesnik.infograce.ui.layer_fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kudesnik.infograce.R
import ru.kudesnik.infograce.ui.utils.ItemMoveCallback
import ru.kudesnik.infograce.databinding.ItemLayerBinding
import ru.kudesnik.infograce.model.entities.Item
import java.util.*


class LayerFragmentAdapter(
    private val context: Context,
    private val updateItem: LayerFragment.DoUpdate
) :
    RecyclerView.Adapter<LayerFragmentAdapter.MyViewHolder>(),
    ItemMoveCallback.ItemTouchHelperContract {
    private lateinit var binding: ItemLayerBinding
    private var itemList = ArrayList<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(data: List<Item>) {
        itemList.addAll(data)
        notifyDataSetChanged()

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item, context: Context) = with(binding) {
            itemName.text = item.name
            val link: Int = item.image
            imageItem.load(link)
            Log.d("listOperations", "Действие 3 - ")

//Slider
            slider.value = item.sliderValue.toFloat()
            textViewItemPercent.text = slider.value.toInt().toString()
            slider.addOnChangeListener { slider, value, fromUser ->
                item.sliderValue = value.toInt()
                updateItem.doUpdateItem(item)
//                sliderValue.setSliderValue(item)
//                setCurrentSlider(value.toInt(), item.id)
                textViewItemPercent.text = value.toInt().toString()

            }
//Switch
            val switch = switchItem
            switchItem.isChecked = item.isCheckedSwitch

            switchItem.setOnCheckedChangeListener { buttonView, isChecked ->
                val check = if (isChecked) "включен" else "выключен"

                Toast.makeText(context, "Переключатель $check", Toast.LENGTH_SHORT).show()
                item.isCheckedSwitch = isChecked
                updateItem.doUpdateItem(item)

//                switchChecked.setCheckedSwitch(item)
//                setCurrentSwitch(isChecked, item.id)
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

//Проверка на видимость Item
            if (item.isVisible) {
                itemName.alpha = 1F
                imageItem.alpha = 1F
                buttonVisibility.alpha = 1F
                switchItem.alpha = 1F
                arrowButton.alpha = 1f
                buttonVisibility.visibility = View.GONE
            } else {
                itemName.alpha = 0.5F
                imageItem.alpha = 0.5f
                buttonVisibility.alpha = 0.5f
                switchItem.alpha = 0.5f
                arrowButton.alpha = 0.5f
                buttonVisibility.visibility = View.VISIBLE
            }
//Item нажатие - открытие вкладки, если Item видимый
            root.setOnClickListener {
                if (item.isVisible) {
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
            }

//Item долгий клик - смена состояния видимости
            root.setOnLongClickListener {
                if (item.isVisible) {
                    itemName.alpha = 0.5F
                    imageItem.alpha = 0.5f
                    buttonVisibility.alpha = 0.5f
                    switchItem.alpha = 0.5f
                    arrowButton.alpha = 0.5f
                    buttonVisibility.visibility = View.VISIBLE
                    item.isVisible = false
                    updateItem.doUpdateItem(item)

                } else {
                    itemName.alpha = 1F
                    imageItem.alpha = 1F
                    buttonVisibility.alpha = 1F
                    switchItem.alpha = 1F
                    arrowButton.alpha = 1f
                    buttonVisibility.visibility = View.GONE
                    item.isVisible = true
                    updateItem.doUpdateItem(item)
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemLayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList.sortedBy { it.position }[position], context = context)
    }

//setCurrentPosition(position)
//        setClickListener(holder);
//
//        holder.image.setOnTouchListener(OnTouchListener { v, event ->
//            if (event.actionMasked === MotionEvent.ACTION_DOWN) {
//                fragmentThis.onStartDrag(holder)
//            }
//            false
//        })


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
}



