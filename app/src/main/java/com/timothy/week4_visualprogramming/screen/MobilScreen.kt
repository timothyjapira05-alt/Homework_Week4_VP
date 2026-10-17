package com.timothy.week4_visualprogramming.screen

import androidx.activity.compose.BackHandler
import com.timothy.week4_visualprogramming.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timothy.week4_visualprogramming.Widgets.CarCard
import com.timothy.week4_visualprogramming.model.Car
import com.timothy.week4_visualprogramming.ui.theme.DarkText
import com.timothy.week4_visualprogramming.ui.theme.OrangeAccent
import com.timothy.week4_visualprogramming.ui.theme.ScreenBg

val carListData = listOf(
    Car(
        1, "Daihatsu Ayla", "Manual", 2023, 15000, "PETROL", "Jakarta", 135_000_000,
        "Mobil LCGC yang lincah, sangat irit bahan bakar, dan cocok untuk perkotaan.",
        R.drawable.ayla, false
    ),
    Car(
        2, "Honda CR-V", "Auto", 2021, 35000, "PETROL", "Surabaya", 385_000_000,
        "SUV keluarga tangguh dengan kabin lapang, fitur keselamatan canggih, dan mesin bertenaga.",
        R.drawable.crv, true
    ),
    Car(
        3, "Honda HR-V", "Auto", 2022, 22000, "PETROL", "Bandung", 320_000_000,
        "Compact SUV berdesain futuristik, panoramic glass roof, dan kenyamanan terbaik.",
        R.drawable.hrv, false
    ),
    Car(
        4, "JAC J5", "Auto", 2023, 12000, "PETROL", "Tangerang", 290_000_000,
        "Sedan modern dengan fitur lengkap, desain aerodinamis, dan efisiensi optimal.",
        R.drawable.j5, false
    ),
    Car(
        5, "MG4 EV", "Auto", 2024, 4500, "PHEV", "Jakarta", 425_000_000,
        "Crossover listrik murni berdesain sporty, akselerasi responsif, dan daya jelajah tinggi.",
        R.drawable.mg4, true
    ),
    Car(
        6, "Toyota Innova Reborn", "Manual", 2020, 48000, "PETROL", "Semarang", 310_000_000,
        "MPV andalan keluarga Indonesia dengan kenyamanan ekstra dan daya tahan tinggi.",
        R.drawable.reborn, true
    ),
    Car(
        7, "Chery Tiggo 9 CSH", "Auto", 2024, 5000, "PHEV", "Jakarta", 650_000_000,
        "SUV plug-in hybrid dengan kabin luas, fitur keselamatan lengkap, dan konsumsi BBM irit.",
        R.drawable.tiggo, true
    ),
    Car(
        8, "Daihatsu Xenia", "Manual", 2022, 28000, "PETROL", "Malang", 185_000_000,
        "MPV 7-seater praktis dan efisien dengan biaya perawatan terjangkau.",
        R.drawable.xenia, false
    ),
    Car(
        9, "Toyota Yaris", "Auto", 2021, 52400, "PETROL", "Semarang", 245_000_000,
        "The Toyota Yaris G AT is a sporty compact hatchback with 7 airbags and 1.5L dual VVT-i engine.",
        R.drawable.yaris, true
    ),
    Car(
        10, "Toyota Yaris Cross", "Auto", 2024, 30500, "HYBRID", "Bandung", 430_000_000,
        "Crossover hybrid yang irit, lincah, dan dilengkapi teknologi EV Mode terkini.",
        R.drawable.yariscross, false
    )
)

@Composable
fun MobilScreen() {
    val cars = remember { carListData.toMutableStateList() }
    var selectedCarId by rememberSaveable { mutableStateOf<Int?>(null) }
    val selectedCar = cars.find { it.id == selectedCarId }

    //toggle favorite: ganti item di list dengan copy yang isFavorite-nya dibalik
    fun toggleFavorite(id: Int) {
        val index = cars.indexOfFirst { it.id == id }
        if (index != -1) {
            cars[index] = cars[index].copy(isFavorite = !cars[index].isFavorite)
        }
    }

    if (selectedCar != null) {
        BackHandler { selectedCarId = null }
        CarDetailScreen(
            car = selectedCar,
            onBack = { selectedCarId = null },
            onFavoriteClick = { toggleFavorite(selectedCar.id) }
        )
    } else {
        CarListScreen(
            cars = cars,
            onCarClick = { selectedCarId = it.id },
            onFavoriteClick = { toggleFavorite(it.id) }
        )
    }
}

@Composable
fun CarListScreen(
    cars: List<Car>,
    onCarClick: (Car) -> Unit,
    onFavoriteClick: (Car) -> Unit
) {
    val favoriteCount = cars.count { it.isFavorite }

    Column(Modifier.fillMaxSize().background(ScreenBg)) {
        Column(Modifier.padding(horizontal = 20.dp).padding(top = 16.dp, bottom = 12.dp)) {
            Box(Modifier.width(32.dp).height(3.dp).background(OrangeAccent, CircleShape))
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Column(Modifier.weight(1f)) {
                    Text(
                        "On Sale",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkText
                    )
                    Text("Find your perfect ride", fontSize = 12.sp, color = Color.Gray)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(1.dp, OrangeAccent.copy(alpha = 0.4f), RoundedCornerShape(50))
                        .background(Color(0xFFFFEFEA), RoundedCornerShape(50))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = OrangeAccent,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "$favoriteCount saved",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = OrangeAccent
                    )
                }
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(cars, key = { it.id }) { car ->
                CarCard(
                    car = car,
                    onClick = { onCarClick(car) },
                    onFavoriteClick = { onFavoriteClick(car) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MobilScreenPreview() {
    MobilScreen()
}
