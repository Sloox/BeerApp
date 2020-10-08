package com.wrightstuff.screens.beerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wrightstuff.beer.BR
import com.wrightstuff.model.Beer
import kotlinx.android.synthetic.main.adapter_item_list_beer.view.*

class BeerAdapter<T>(
    @LayoutRes val layout: Int,
    private val diff: DiffCallback<T>? = null,
    private val onClick: OnBaseAdapterClickListener<T>? = null
) :
    RecyclerView.Adapter<BeerAdapter<T>.ViewHolder>() {

    constructor(@LayoutRes layout: Int, onClick: OnBaseAdapterClickListener<T>? = null) : this(layout, null, onClick)

    var items: List<T> = listOf()
        set(value) {
            if (diff != null) {
                diff.oldItems = field
                diff.newItems = value
                val result = DiffUtil.calculateDiff(diff)
                field = value
                result.dispatchUpdatesTo(this)
            } else {
                field = value
                notifyDataSetChanged()
            }
        }

    class OnBaseAdapterClickListener<T>(val clickListener: (result: T) -> Unit) {
        fun onClick(result: T) = clickListener(result)
    }

    inner class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setFromBeerItem(item: T?) {
            if (item != null && item is Beer) {
                val beer = item as Beer
                setBeerImage(beer, itemView.beerView)
                //set more info
            }
        }

        private fun setBeerImage(beer: Beer, imageView: ImageView) {
            beer.imageUrl?.let {
                Glide.with(itemView.context)
                    .load(beer.imageUrl)
                    .into(imageView)
            }
        }

        var item: T? = null
            set(value) {
                field = value
                binding.setVariable(BR.result, value)
                binding.setVariable(BR.onClick, onClick)
                binding.executePendingBindings()
            }
    }

    abstract class DiffCallback<T> : DiffUtil.Callback() {
        abstract var oldItems: List<T>
        abstract var newItems: List<T>

        override fun getOldListSize(): Int = oldItems.size
        override fun getNewListSize(): Int = newItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = items[position]
        holder.setFromBeerItem(holder.item)
    }
}