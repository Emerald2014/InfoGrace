package ru.kudesnik.infograce

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kudesnik.infograce.databinding.ItemLayerBinding

class LayerFragmentAdapter(private val names: List<String>) :
    RecyclerView.Adapter<LayerFragmentAdapter.MyViewHolder>() {
    private lateinit var binding: ItemLayerBinding


  inner  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String) = with(binding) {
itemName.text = name
imageItem.load(R.drawable.item_temp)
            //            val cardView = included.baseCardView

            root.setOnClickListener {
//                Toast.makeText(require(), "ToastInFragment", Toast.LENGTH_SHORT).show()
                if (hiddenView.visibility == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(baseCardView, AutoTransition())
                    hiddenView.visibility = View.GONE
                    arrowButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                } else {
                    TransitionManager.beginDelayedTransition(
                        baseCardView, AutoTransition()
                    )
                    hiddenView.visibility = View.VISIBLE
                    arrowButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                } }
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
        holder.bind(names[position])
    }

    override fun getItemCount(): Int = names.size
}
