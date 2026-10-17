package com.timothy.week4_visualprogramming.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.timothy.week4_visualprogramming.ui.theme.CardBorder
import com.timothy.week4_visualprogramming.R
import com.timothy.week4_visualprogramming.Widgets.classColor
import com.timothy.week4_visualprogramming.Widgets.HeroCard
import com.timothy.week4_visualprogramming.model.Hero
import com.timothy.week4_visualprogramming.ui.theme.CardBg
import com.timothy.week4_visualprogramming.ui.theme.DarkBg
import com.timothy.week4_visualprogramming.ui.theme.Gold

val heroListData = listOf(
    Hero(
        1,
        "Alistair",
        "High Templar",
        "MYTHIC",
        50,
        5600,
        1650,
        1800,
        650,
        800,
        380,
        540,
        22,
        105,
        "Wrath of the Heavens",
        "Calls down a celestial hammer, stunning all enemies for 3 seconds and taunting survivors.",
        R.drawable.paladin,
        true
    ),
    Hero(2, "Theresa", "Divine Oracle", "MYTHIC", 49, 5480, 1100, 1200, 2100, 2400, 210, 300, 12, 90,
        "Sacred Chant", "Restores HP to all allies and removes one negative effect.",
        R.drawable.cleric),
    Hero(3, "Vespera", "Shadowblade", "MYTHIC", 48, 5350, 890, 950, 410, 600, 450, 220, 38, 140,
        "Umbral Strike", "Teleports behind the target and strikes for triple damage.",
        R.drawable.rogue),
    Hero(4, "Elaria", "Archmage", "MYTHIC", 47, 5120, 450, 600, 1850, 2000, 520, 150, 18, 95,
        "Arcane Storm", "Rains arcane bolts on all enemies for 5 seconds.",
        R.drawable.archmage, true),
    Hero(5, "Gorak", "Berserker", "LEGENDARY", 45, 4800, 300, 2000, 120, 400, 610, 280, 30, 100,
        "Blood Rage", "Gains attack speed and lifesteal as his HP drops.",
        R.drawable.berserker, true)
)

val filterListData = listOf(
    "All (14)", "Berserker (2)", "Archmage (3)", "Shadowblade (2)", "Oracle (2)", "Templar (1)"
)

@Composable
fun PartyRosterScreen() {
    var selectedFilter by rememberSaveable { mutableStateOf(filterListData.first()) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var expandedHeroId by rememberSaveable { mutableStateOf<Int?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(DarkBg),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { RosterHeader() }
        item { SquadPanel(squad = heroListData.filter { it.inSquad }) }
        item { RosterSearchBar(query = searchQuery, onChange = { searchQuery = it }) }
        item {
            FilterRow(
                filterList = filterListData,
                selected = selectedFilter,
                onSelect = { selectedFilter = it }
            )
        }
        items(heroListData, key = { it.id }) { hero ->
            HeroCard(
                hero = hero,
                expanded = expandedHeroId == hero.id,
                onClick = { expandedHeroId = if (expandedHeroId == hero.id) null else hero.id }
            )
        }
    }
}

@Composable
private fun RosterHeader() {
    Row(verticalAlignment = Alignment.Top) {
        Column(Modifier.weight(1f)) {
            Text(
                "Party Roster",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Text("Guild Vanguard • 14 Heroes Available", fontSize = 11.sp, color = Color.Gray)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Gold.copy(alpha = 0.6f), RoundedCornerShape(50))
                .background(Gold.copy(alpha = 0.12f), RoundedCornerShape(50))
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Icon(
                Icons.Filled.Star,
                contentDescription = null,
                tint = Gold,
                modifier = Modifier.size(12.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text("Expedition", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Gold)
        }
    }
}

@Composable
private fun SquadPanel(squad: List<Hero>) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(CardBg, RoundedCornerShape(16.dp))
            .border(1.dp, Gold.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(7.dp).background(Color(0xFF2ECC71), RoundedCornerShape(50)))
            Spacer(Modifier.width(6.dp))
            Text(
                "EXPEDITION SQUAD (${squad.size}/4)",
                modifier = Modifier.weight(1f),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Text(
                "⚔ ${squad.sumOf { it.power }} CP",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Gold
            )
        }

        Spacer(Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            squad.forEach { hero ->
                Image(
                    painter = painterResource(hero.imgRes),
                    contentDescription = hero.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.5.dp, classColor(hero.heroClass), RoundedCornerShape(10.dp))
                )
            }
            repeat((4 - squad.size).coerceAtLeast(0)) {
                Box(
                    Modifier.size(44.dp).border(1.dp, CardBorder, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", fontSize = 18.sp, color = Color.Gray)
                }
            }
            Spacer(Modifier.weight(1f))
            Box(
                Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Gold)
                    .clickable { }
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Text("Deploy ⚡", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

@Composable
private fun RosterSearchBar(query: String, onChange: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onChange,
        placeholder = { Text("Search by hero name, class, or rarity...", fontSize = 12.sp) },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = CardBg,
            unfocusedContainerColor = CardBg,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray,
            focusedLeadingIconColor = Color.Gray,
            unfocusedLeadingIconColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun FilterRow(
    filterList: List<String>,
    selected: String,
    onSelect: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(filterList) { filter ->
            val isSelected = filter == selected
            Text(
                filter,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(if (isSelected) Gold.copy(alpha = 0.15f) else CardBg)
                    .border(1.dp, if (isSelected) Gold else CardBorder, RoundedCornerShape(50))
                    .clickable { onSelect(filter) }
                    .padding(horizontal = 14.dp, vertical = 7.dp),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = if (isSelected) Gold else Color.Gray
            )
        }
        item {
            Text(
                "Sort: Power",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(CardBg)
                    .border(1.dp, Gold.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                    .clickable { }
                    .padding(horizontal = 10.dp, vertical = 7.dp),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Gold
            )
        }
    }
}

@Composable
fun PartyScreen() {
    PartyRosterScreen()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PartyScreenPreview() {
    PartyRosterScreen()
}
