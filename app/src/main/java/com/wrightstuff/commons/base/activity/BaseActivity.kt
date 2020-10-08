package com.wrightstuff.commons.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.wrightstuff.di.presentation.PresentationModule
import com.wrightstuff.application.BeerApplication
import com.wrightstuff.di.application.BeerAppComponent
import com.wrightstuff.di.presentation.PresentationComponent

abstract class BaseActivity(@LayoutRes private val layout: Int, private val args: Bundle? = null) :
    AppCompatActivity() {
    private var mIsInjectorUsed = false

    @UiThread
    protected fun getPresentationComponent(): PresentationComponent {
        if (mIsInjectorUsed) {
            throw RuntimeException("there is no need to use injector more than once")
        }
        mIsInjectorUsed = true
        return getApplicationComponent().newPresentationComponent(PresentationModule(this))
    }

    private fun getApplicationComponent(): BeerAppComponent {
        return (application as BeerApplication).getBeerAppComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }
}