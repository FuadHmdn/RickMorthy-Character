package com.rickmorthy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val description: String,
    val photo: Int
) : Parcelable
