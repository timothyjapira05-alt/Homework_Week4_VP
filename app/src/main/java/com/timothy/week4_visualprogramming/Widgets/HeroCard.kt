package com.timothy.week4_visualprogramming.Widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timothy.week4_visualprogramming.ui.theme.HpRed
import com.timothy.week4_visualprogramming.ui.theme.MpBlue
import com.timothy.week4_visualprogramming.model.Hero
import com.timothy.week4_visualprogramming.ui.theme.CardBg
import com.timothy.week4_visualprogramming.ui.theme.CardBorder
import com.timothy.week4_visualprogramming.ui.theme.Gold

fun classColor(heroClass: String): Color {
    return when {
        heroClass.contains("Templar") -> Color(0xFFFFB400)
        heroClass.contains("Oracle") -> Color(0xFF2ECC71)
        heroClass.contains("Shadow") -> Color(0xFFC026D3)
        heroClass.contains("mage") -> Color(0xFF22D3EE)
        heroClass.contains("Berserker") -> Color(0xFFFF5A5F)
        else -> Color.Gray
    }
}

fun rarityColor(rarity: String): Color {
    return when (rarity) {
        "MYTHIC" -> Color(0xFFFF3B5C)
        "LEGENDARY" -> Color(0xFFFFB400)
        else -> Color.Gray
    }
}

@Composable
fun HeroCard(
    hero: Hero,
    expanded: Boolean,
    onClick: () -> Unit
) {
    val accent = classColor(hero.heroClass)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        border = BorderStroke(1.dp, if (hero.inSquad) Gold else CardBorder)
    ) {
        Column(Modifier.padding(12.dp)) {
            //baris atas: rarity, class, CP
            Row(verticalAlignment = Alignment.CenterVertically) {
                HeroTag(hero.rarity, rarityColor(hero.rarity))
                Spacer(Modifier.width(6.dp))
                HeroTag(hero.heroClass, accent)
                Spacer(Modifier.weight(1f))
                Text(
                    "⚔ ${hero.power} CP",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gold
                )
                if (hero.inSquad) {
                    Spacer(Modifier.width(6.dp))
                    Text(
                        "IN SQUAD",
                        modifier = Modifier
                            .border(1.dp, Gold, RoundedCornerShape(6.dp))
                            .padding(horizontal = 5.dp, vertical = 2.dp),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        color = Gold
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            //avatar, nama, bar HP & MP
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(70.dp)) {
                    Image(
                        painter = painterResource(hero.imgRes),
                        contentDescription = hero.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.5.dp, accent, RoundedCornerShape(12.dp))
                    )
                    Text(
                        "Lvl ${hero.level}",
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(3.dp)
                            .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 1.dp),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(Modifier.width(14.dp))

                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            hero.name,
                            modifier = Modifier.weight(1f),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Icon(
                            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    StatBar("HP", hero.hp, hero.maxHp, HpRed)
                    Spacer(Modifier.height(4.dp))
                    StatBar("MP", hero.mp, hero.maxMp, MpBlue)
                }
            }

            //dropdown detail
            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(Modifier.height(12.dp))
                    Box(Modifier.fillMaxWidth().height(1.dp).background(CardBorder))
                    Spacer(Modifier.height(12.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        StatBox("ATK", hero.atk.toString(), Color(0xFFFF8A00), Modifier.weight(1f))
                        StatBox("DEF", hero.def.toString(), Color(0xFF5B8DEF), Modifier.weight(1f))
                        StatBox("CRIT", "${hero.crit}%", Color(0xFFFF3B5C), Modifier.weight(1f))
                        StatBox("SPD", hero.spd.toString(), Color(0xFF22D3EE), Modifier.weight(1f))
                    }

                    Spacer(Modifier.height(10.dp))

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF1C1C2B), RoundedCornerShape(10.dp))
                            .border(1.dp, CardBorder, RoundedCornerShape(10.dp))
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = null,
                                tint = Gold,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                hero.skillName,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                        Text(hero.skillDesc, fontSize = 10.sp, lineHeight = 14.sp, color = Color.Gray)
                    }

                    Spacer(Modifier.height(10.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        ActionButton("🩹 Heal (+250 HP)", Color(0xFF2ECC71), Modifier.weight(1f))
                        ActionButton("⚔ Add to Squad", Gold, Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroTag(text: String, color: Color) {
    Text(
        text,
        modifier = Modifier
            .background(color.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
            .border(1.dp, color.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp),
        fontSize = 9.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
}

//bar HP / MP, panjang isian = value dibagi max
@Composable
fun StatBar(label: String, value: Int, max: Int, color: Color) {
    val fraction = (value.toFloat() / max.coerceAtLeast(1)).coerceIn(0f, 1f)

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            label,
            modifier = Modifier.width(22.dp),
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Box(
            Modifier
                .weight(1f)
                .height(7.dp)
                .clip(CircleShape)
                .background(Color(0xFF0B0B12))
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction)
                    .clip(CircleShape)
                    .background(color)
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(
            "$value / $max",
            modifier = Modifier.width(64.dp),
            fontSize = 9.sp,
            color = Color.Gray,
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun StatBox(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color(0xFF1C1C2B), RoundedCornerShape(10.dp))
            .border(1.dp, CardBorder, RoundedCornerShape(10.dp))
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = color)
        Text(value, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Composable
private fun ActionButton(text: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color.copy(alpha = 0.15f))
            .border(1.dp, color, RoundedCornerShape(10.dp))
            .clickable { } //logic tidak perlu berfungsi
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = color)
    }
}
