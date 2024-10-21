package com.example.superheros

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheros.model.Hero
import com.example.superheros.model.HeroRepository
import com.example.superheros.ui.theme.Shapes
import com.example.superheros.ui.theme.SuperherosTheme

class HeroesScreen {

    @Composable
    fun HeroesList(
        heroes: List<Hero>,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(modifier = modifier) {
            items(heroes) { hero ->
                HeroListItem(
                    hero = hero,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }

    }

    @Composable
    fun HeroListItem(hero: Hero, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier.clip(Shapes.medium),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .sizeIn(minHeight = 72.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(modifier = Modifier.weight(1F)) {
                    Text(
                        text = stringResource(hero.name),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        text = stringResource(hero.description),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Image(
                        painter = painterResource(hero.image),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.TopCenter
                    )
                }
            }
        }
    }

    @Preview("Light Them")
    @Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Preview(showBackground = true)
    @Composable
    fun PreviewHeroesListItem() {
        Surface(color = MaterialTheme.colorScheme.background) {
            SuperherosTheme { HeroListItem(HeroRepository().loadHeroes()[1]) }
        }
    }

    @Preview("Heroes List", showBackground = true)
    @Composable
    fun PreviewHeroesList() {
        SuperherosTheme {
            Surface(color = MaterialTheme.colorScheme.background) { HeroesList(HeroRepository().loadHeroes()) }
        }
    }

}