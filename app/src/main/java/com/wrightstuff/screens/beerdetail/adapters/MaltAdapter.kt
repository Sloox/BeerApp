package com.wrightstuff.screens.beerdetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wrightstuff.beer.R
import com.wrightstuff.model.Hop
import com.wrightstuff.model.Malt
import kotlinx.android.synthetic.main.hop_item.view.*
import kotlinx.android.synthetic.main.malt_item.view.*
import kotlinx.android.synthetic.main.mash_item.view.*


class MaltAdapter : RecyclerView.Adapter<MaltAdapter.HopsHolder>() {
    private var malt: List<Malt> = emptyList()

    fun setMalt(items: List<Malt>) {
        malt = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HopsHolder =
        HopsHolder(LayoutInflater.from(parent.context).inflate(R.layout.malt_item, parent, false))

    override fun getItemCount() = malt.size

    override fun onBindViewHolder(holder: HopsHolder, position: Int) = holder.bind(malt[position])

    class HopsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(malt: Malt) {
            if (malt.name != null) {
                itemView.malt_name.visibility = View.VISIBLE
                itemView.malt_name.text = "${malt.name}"
            } else {
                itemView.malt_name.visibility = View.GONE
            }
            if (malt.amount != null) {
                itemView.malt_amount.visibility = View.VISIBLE
                itemView.malt_amount.text = "- ${malt.amount!!.value} ${malt.amount!!.unit}"
            } else {
                itemView.malt_amount.visibility = View.GONE
            }
        }
    }
}