package com.wrightstuff.screens.beerdetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wrightstuff.beer.R
import com.wrightstuff.commons.extensions.loge
import com.wrightstuff.model.MashTemp
import kotlinx.android.synthetic.main.mash_item.view.*

class MethodAdapter : RecyclerView.Adapter<MethodAdapter.MethodHolder>() {
    private var mash: List<MashTemp> = emptyList()

    fun setMash(items: List<MashTemp>) {
        mash = items
        loge("data set: ${items.size}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MethodHolder =
        MethodHolder(LayoutInflater.from(parent.context).inflate(R.layout.mash_item, parent, false))

    override fun getItemCount() = mash.size

    override fun onBindViewHolder(holder: MethodHolder, position: Int) {
        holder.bind(mash[position])
    }

    class MethodHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(mash: MashTemp) {
            if (mash.temp != null) {
                itemView.mash_temp.visibility = View.VISIBLE
                itemView.mash_temp.text = "${mash.temp!!.value} ${mash.temp!!.unit}"
            } else {
                itemView.mash_temp.visibility = View.GONE
            }
            if (mash.duration == null) {
                itemView.mash_duration.visibility = View.VISIBLE
                itemView.mash_duration.text = " for ${mash.duration} s"
            } else {
                itemView.mash_duration.visibility = View.GONE
            }
        }
    }
}