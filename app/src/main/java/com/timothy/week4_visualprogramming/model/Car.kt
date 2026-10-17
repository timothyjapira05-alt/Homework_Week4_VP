package com.timothy.week4_visualprogramming.model

import androidx.annotation.DrawableRes

data class Car(
    val id: Int,
    val name: String,
    val transmission: String,
    val year: Int,
    val mileageKm: Int,
    val fuelType: String,
    val location: String,
    val price: Long,
    val description: String,
    @DrawableRes val imgRes: Int,
    val isFavorite: Boolean = false
)
