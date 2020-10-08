package com.wrightstuff.screens.beerdetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wrightstuff.beer.R
import com.wrightstuff.model.Hop
import kotlinx.android.synthetic.main.hop_item.view.*

class HopsAdapter : RecyclerView.Adapter<HopsAdapter.HopsHolder>() {
    private var hops: List<Hop> = emptyList()

    fun setHops(items: List<Hop>) {
        hops = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HopsHolder =
        HopsHolder(LayoutInflater.from(parent.context).inflate(R.layout.hop_item, parent, false))

    override fun getItemCount() = hops.size

    override fun onBindViewHolder(holder: HopsHolder, position: Int) = holder.bind(hops[position], (hops.size>1)&&(hops.lastIndex != position))

    class HopsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hop: Hop, showDivider: Boolean) {
            if (hop.attribute != null) {
                itemView.hop_attribute.visibility = View.VISIBLE
                itemView.hop_attribute.text = "Attribute: ${hop.attribute}"
            } else {
                itemView.hop_attribute.visibility = View.GONE
            }
            if (hop.name != null) {
                itemView.hop_name.visibility = View.VISIBLE
                itemView.hop_name.text = "${hop.name}"
            } else {
                itemView.hop_name.visibility = View.GONE
            }
            if (hop.add != null) {
                itemView.hop_add.visibility = View.VISIBLE
                itemView.hop_add.text = "When to add: ${hop.add}"
            } else {
                itemView.hop_add.visibility = View.GONE
            }
            if (hop.amount != null) {
                itemView.hop_amount.visibility = View.VISIBLE
                itemView.hop_amount.text = "- ${hop.amount!!.value} ${hop.amount!!.unit}"
            } else {
                itemView.hop_amount.visibility = View.GONE
            }
            if (!showDivider) {
                itemView.item_divider.visibility = View.GONE
            }
        }
    }
}