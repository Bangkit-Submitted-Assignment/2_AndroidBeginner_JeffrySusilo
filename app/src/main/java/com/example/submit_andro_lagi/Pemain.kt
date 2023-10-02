package com.example.submit_andro_lagi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pemain(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
