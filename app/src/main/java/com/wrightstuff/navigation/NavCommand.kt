package com.wrightstuff.navigation

import androidx.annotation.IdRes
import com.wrightstuff.beer.R

/**
 * Class that handles navigation and abstracts its implementation away into the baseFragment & baseViewmodels
 * Can add direct navigation here by matching the navCommand cmdID to the itemID defined in the navigation.xml file
 * There are many ways to navigate via androidX navigation, this is just one way.
 * This eases up direct navigation via custom ui components
 * */
sealed class NavCommand(@IdRes val cmdID: Int) {
    object Back : NavCommand(-1)
    data class BeerDetailsFragment(val beerID: Int) : NavCommand(R.id.nav_beer_details)
    object BeerSelectionFragment : NavCommand(R.id.nav_beer_list)
}