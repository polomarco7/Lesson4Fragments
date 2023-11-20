package com.example.lesson3fragments.part2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val id: Int? = null,
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    var imageUrl: String = ""
): Parcelable