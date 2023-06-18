package com.example.entregasuperpoderesandroid.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.entregasuperpoderesandroid.R
import com.example.entregasuperpoderesandroid.data.model.SuperHeroCharacter
import com.example.entregasuperpoderesandroid.ui.theme.RedWine
import com.example.entregasuperpoderesandroid.ui.viewModels.CharactersViewModel

@Composable
fun SuperHeroCharacterListScreen(viewModel: CharactersViewModel, onCharacterClick: (Int) -> Unit = {_->} ) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getHerosCharacter()
    }

    SuperHeroCharacterListScreenContent(state){ hero->
        onCharacterClick(hero.id)

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroCharacterListScreenContent(characters: List<SuperHeroCharacter>, onSuperHeroListClicked: (SuperHeroCharacter) ->Unit) {

    val scaffoldS = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarHeros()
        }
    ) {
        LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = it) {
            items(characters, key = {it.id}) { hero ->
                SuperHeroItem(hero = hero, onHeroClick = onSuperHeroListClicked)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHeros() {

    CenterAlignedTopAppBar(title = {
        Text(text = stringResource(R.string.listado_superheros_marvel))
    })
}

@Preview
@Composable
fun TopBarHeros_Preview() {
    TopBarHeros()
}

@Composable
fun SuperHeroItem(hero: SuperHeroCharacter, modifier: Modifier = Modifier, onHeroClick: (SuperHeroCharacter) -> Unit) {

    Card(
        colors = CardDefaults.cardColors(Color.DarkGray),
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clickable { onHeroClick(hero) }
    ) {
        AsyncImage(
                model = hero.photo,
                contentDescription = "${hero.name} photo",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = hero.name,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp))
            Icon(imageVector = Icons.Sharp.FavoriteBorder,
                tint = RedWine,
                contentDescription = "Favorite Icon",
                modifier = Modifier
                    .padding(8.dp)
                    .size(35.dp)
            )
        }
    }

}

@Preview
@Composable
fun SuperHeroItem_Preview() {
    SuperHeroItem(SuperHeroCharacter(123123, "Goku", "adfgadfg", "adgdf")){

    }
}