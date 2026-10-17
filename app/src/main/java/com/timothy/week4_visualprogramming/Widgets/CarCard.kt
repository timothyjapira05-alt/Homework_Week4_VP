package com.timothy.week4_visualprogramming.Widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timothy.week4_visualprogramming.Util.formatKm
import com.timothy.week4_visualprogramming.model.Car
import com.timothy.week4_visualprogramming.ui.theme.DarkText
import com.timothy.week4_visualprogramming.ui.theme.OrangeAccent

@Composable
fun CarCard(
    car: Car,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.width(100.dp).height(80.dp)) {
                Image(
                    painter = painterResource(car.imgRes),
                    contentDescription = car.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp))
                )
                FuelBadge(
                    fuel = car.fuelType,
                    modifier = Modifier.align(Alignment.BottomStart).padding(4.dp)
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        car.name,
                        modifier = Modifier.weight(1f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )
                    FavoriteButton(
                        isFavorite = car.isFavorite,
                        onClick = onFavoriteClick,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Text(
                    car.transmission,
                    modifier = Modifier
                        .background(Color(0xFFF1EEEB), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp),
                    fontSize = 10.sp,
                    color = Color.Gray
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(6.dp).background(OrangeAccent, CircleShape))
                    Spacer(Modifier.width(4.dp))
                    Text(
                        car.year.toString(),
                        modifier = Modifier.weight(1f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )
                    Text(formatKm(car.mileageKm), fontSize = 11.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (isFavorite) OrangeAccent else Color.LightGray
        )
    }
}

@Composable
fun FuelBadge(fuel: String, modifier: Modifier = Modifier) {
    val (bgColor, textColor) = when (fuel.uppercase()) {
        "PETROL" -> Color(0xFFFFF0D6) to Color(0xFFE88A00)
        "PHEV" -> Color(0xFFE9E3FA) to Color(0xFF6D4AD8)
        "HYBRID" -> Color(0xFFD9F2EC) to Color(0xFF0E8A6D)
        else -> Color(0xFFEEEEEE) to Color.DarkGray
    }
    Text(
        fuel.uppercase(),
        modifier = modifier
            .background(bgColor, RoundedCornerShape(6.dp))
            .padding(horizontal = 6.dp, vertical = 3.dp),
        fontSize = 8.sp,
        fontWeight = FontWeight.Bold,
        color = textColor
    )
}
