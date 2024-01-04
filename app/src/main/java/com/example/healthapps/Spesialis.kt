package com.example.healthapps

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Spesialis(
    var name: String,
    var description: String,
    var photo: String
) : Parcelable