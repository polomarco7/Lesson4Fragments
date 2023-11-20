package com.example.lesson3fragments.ui.main

import android.os.Bundle

interface OnButtonsClickListener {
    fun onForwardButtonClicked(
        targetName: String,
        bundle: Bundle? = null
    )

    fun onBackClicked(
        targetName: String,
    )
}