package com.timothy.week4_visualprogramming.model

import androidx.annotation.DrawableRes

data class Hero(
    val id: Int,
    val name: String,
    val heroClass: String,
    val rarity: String,
    val level: Int,
    val power: Int,
    val hp: Int,
    val maxHp: Int,
    val mp: Int,
    val maxMp: Int,
    val atk: Int,
    val def: Int,
    val crit: Int,
    val spd: Int,
    val skillName: String,
    val skillDesc: String,
    @DrawableRes val imgRes: Int,
    val inSquad: Boolean = false
)
