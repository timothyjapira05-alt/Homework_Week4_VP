package com.timothy.week4_visualprogramming.Util

import java.util.Locale

fun formatRupiah(value: Long): String {
    return "Rp " + String.format(Locale("id", "ID"), "%,d", value)
}

fun formatKm(value: Int): String {
    return String.format(Locale("id", "ID"), "%,d km", value)
}
