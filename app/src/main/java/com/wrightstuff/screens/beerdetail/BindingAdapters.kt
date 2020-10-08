package com.wrightstuff.screens.beerdetail

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wrightstuff.model.Hop
import com.wrightstuff.model.Malt
import com.wrightstuff.model.Method
import com.wrightstuff.screens.beerdetail.adapters.HopsAdapter
import com.wrightstuff.screens.beerdetail.adapters.MaltAdapter
import com.wrightstuff.screens.beerdetail.adapters.MethodAdapter

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, method: Method?) {
    method?.mashTemp?.let {
        if (listView.adapter == null) listView.adapter = MethodAdapter()
        (listView.adapter as MethodAdapter).setMash(it)
    }
}

@BindingAdapter("app:items")
fun setMalts(listView: RecyclerView, malts: List<Malt>?) {
    malts?.let {
        if (listView.adapter == null) listView.adapter = MaltAdapter()
        (listView.adapter as MaltAdapter).setMalt(it)
    }
}

@BindingAdapter("app:items")
fun setHops(listView: RecyclerView, hops: List<Hop>?) {
    hops?.let {
        if (listView.adapter == null) listView.adapter = HopsAdapter()
        (listView.adapter as HopsAdapter).setHops(it)
    }
}