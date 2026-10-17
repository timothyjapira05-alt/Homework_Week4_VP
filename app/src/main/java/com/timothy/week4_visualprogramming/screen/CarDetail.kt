package com.timothy.week4_visualprogramming.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timothy.week4_visualprogramming.R
import com.timothy.week4_visualprogramming.Util.formatKm
import com.timothy.week4_visualprogramming.Util.formatRupiah
import com.timothy.week4_visualprogramming.Widgets.FavoriteButton
import com.timothy.week4_visualprogramming.Widgets.FuelBadge
import com.timothy.week4_visualprogramming.model.Car
import com.timothy.week4_visualprogramming.ui.theme.DarkText
import com.timothy.week4_visualprogramming.ui.theme.OrangeAccent
import com.timothy.week4_visualprogramming.ui.theme.ScreenBg

@Composable
fun CarDetailScreen(
    car: Car,
    onBack: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Column(Modifier.fillMaxSize().background(ScreenBg)) {
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                Image(
                    painter = painterResource(car.imgRes),
                    contentDescription = car.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(260.dp)
                )
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp)
                        .size(36.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = DarkText,
                        modifier = Modifier.size(18.dp)
                    )
                }
                FuelBadge(
                    fuel = car.fuelType,
                    modifier = Modifier.align(Alignment.BottomStart).padding(start = 16.dp, bottom = 28.dp)
                )
            }

            Column(
                Modifier
                    .offset(y = (-16).dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(ScreenBg)
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        car.name,
                        modifier = Modifier.weight(1f),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkText
                    )
                    FavoriteButton(isFavorite = car.isFavorite, onClick = onFavoriteClick)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(6.dp).background(OrangeAccent, CircleShape))
                    Spacer(Modifier.width(6.dp))
                    Text(car.location, fontSize = 12.sp, color = Color.Gray)
                }

                Spacer(Modifier.height(14.dp))

                Text(
                    formatRupiah(car.price),
                    modifier = Modifier
                        .border(1.dp, OrangeAccent.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                        .background(Color(0xFFFFEFEA), RoundedCornerShape(10.dp))
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    fontWeight = FontWeight.Bold,
                    color = OrangeAccent
                )

                Spacer(Modifier.height(20.dp))
                SectionLabel("SPECIFICATIONS")
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SpecCard("YEAR", car.year.toString(), Modifier.weight(1f))
                    SpecCard("MILEAGE", formatKm(car.mileageKm), Modifier.weight(1f))
                }
                Spacer(Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SpecCard("TRANSMISSION", car.transmission, Modifier.weight(1f))
                    SpecCard(
                        "FUEL TYPE",
                        car.fuelType.lowercase().replaceFirstChar { it.uppercase() },
                        Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(20.dp))
                SectionLabel("ABOUT THIS CAR")
                Text(car.description, fontSize = 12.sp, lineHeight = 18.sp, color = Color.Gray)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ScreenBg)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedIconButton(
                onClick = onFavoriteClick,
                border = BorderStroke(1.dp, OrangeAccent.copy(alpha = 0.5f)),
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = if (car.isFavorite) OrangeAccent else Color.LightGray
                )
            }
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = OrangeAccent,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f).height(48.dp)
            ) {
                Text("Contact Seller", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text,
        modifier = Modifier.padding(bottom = 8.dp),
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 1.sp,
        color = Color.Gray
    )
}

@Composable
private fun SpecCard(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEDE7E3))
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(label, fontSize = 9.sp, letterSpacing = 1.sp, color = Color.Gray)
            Spacer(Modifier.height(4.dp))
            Text(value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkText)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CarDetailScreenPreview() {
    CarDetailScreen(
        car = Car(
            5, "Toyota Yaris", "Auto", 2021, 52400, "PETROL", "Semarang", 245_000_000,
            "The Toyota Yaris G AT is a sporty compact hatchback.",
            R.drawable.yaris, true
        ),
        onBack = {},
        onFavoriteClick = {}
    )
}
