package ru.kudesnik.infograce.ui.layer_fragment

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ru.kudesnik.infograce.ui.utils.ItemMoveCallback
import ru.kudesnik.infograce.databinding.TestItemBinding
import java.util.*
import ru.kudesnik.infograce.model.*



class LayerFragmentAdapterTest(
    private val items: List<Int>,
    private val context: Context

) :
    RecyclerView.Adapter<LayerFragmentAdapterTest.MyViewHolder>(),
    ItemMoveCallback.ItemTouchHelperContract {
    private lateinit var binding: TestItemBinding
//    val fragmentThis = fragment

    //    private lateinit var context:Context
    private var sliderConst = ""
    private var switchConst = ""

    var itemList = mutableListOf<Int>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Int, context: Context) = with(binding) {
            title.text = item.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = TestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)

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
    fun onItemDismiss(position: Int) {
        itemList.removeAt(position);
        notifyItemRemoved(position);
    }

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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position], context = context)

    }


}


interface OnItemClickListener {
    fun onItemClick(view: View?, position: Int)
    fun onItemLongClick(view: View?, position: Int)
}


