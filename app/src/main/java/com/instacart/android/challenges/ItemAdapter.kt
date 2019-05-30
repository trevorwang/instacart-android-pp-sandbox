package com.instacart.android.challenges

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instacart.android.challenges.network.DeliveryItem
import com.squareup.picasso.Picasso
import java.util.*

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private val items = ArrayList<DeliveryItem>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)
        private val imageView = itemView.findViewById<ImageView>(R.id.image_view)
        private val tvVolume = itemView.findViewById<TextView>(R.id.tvVolume)
        fun bind(row: DeliveryItem) {
            textView.text = row.name
            tvVolume.text = "${row.count}"
            Picasso.get().load(row.imageUrl).placeholder(R.mipmap.ic_launcher).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(newItems: List<DeliveryItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
