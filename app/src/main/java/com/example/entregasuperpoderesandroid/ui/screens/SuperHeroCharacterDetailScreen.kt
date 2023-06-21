package com.example.entregasuperpoderesandroid.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.entregasuperpoderesandroid.data.model.Comic
import com.example.entregasuperpoderesandroid.R
import com.example.entregasuperpoderesandroid.data.model.Serie
import com.example.entregasuperpoderesandroid.data.model.SuperHeroCharacter
import com.example.entregasuperpoderesandroid.ui.theme.RedWine
import com.example.entregasuperpoderesandroid.ui.theme.Rose
import com.example.entregasuperpoderesandroid.ui.viewModels.CharacterDetailViewModel

@Composable
fun SuperHeroCharacterDetailScreen(viewModel: CharacterDetailViewModel,
                                   characterID: Int, onFavClicked: (Boolean) -> Unit = {_->}) {

    val hero by viewModel.hero.collectAsState()
    val series by viewModel.series.collectAsState()
    val comics by viewModel.comics.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getHeroCharacter(characterID)
        viewModel.getComicsByHero(characterID)
        viewModel.getSeriesByHero(characterID)
    }
    hero?.let { SuperHeroCharacterDetailContent(it, series, comics)}
}
@Composable
fun SuperHeroCharacterDetailContent(hero: SuperHeroCharacter, series: List<Serie>, comics: List<Comic>) {

        GeneralInformationView(hero, series, comics)
}

@Preview(showBackground = true)
@Composable
fun SuperHeroCharacterDetailContent_Preview() {
    SuperHeroCharacterDetailContent(
        SuperHeroCharacter(123, "Capitana Marvel",
stringResource(R.string.lorem_impsu), "", true),
        listOf(Serie(123, "Algo", stringResource(R.string.lorem_impsu), 2020, 2021, "", )),
        listOf(Comic(23, "Otra cosa", stringResource(R.string.lorem_impsu), "")))
}

@Composable
fun GeneralInformationView(hero: SuperHeroCharacter, series: List<Serie>, comics: List<Comic>) {

   Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

       HeadlineComponent(hero)
       FavButton(hero.favorite)
       hero.description?.let { DescriptionModule(it) }
       TabsForSeriesComics(series, comics)
    }
}

@Preview(showBackground = true)
@Composable
fun GeneralInformationView_Preview() {
    GeneralInformationView(
        SuperHeroCharacter(123, "Capitana Marvel", stringResource(R.string.lorem_impsu), "", true),
        listOf(Serie(123, "Algo", "No", 2020, 2021, "", )),
        listOf(Comic(23, "Otra cosa", "NO", "")))
}

@Composable
fun HeadlineComponent(hero: SuperHeroCharacter, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = hero.photo,
            contentDescription = "${hero.name} photo",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(text = hero.name,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.DarkGray,
            modifier = Modifier.weight(2f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeadlineComponent_Preview() {
    HeadlineComponent(SuperHeroCharacter(123, "Capitana Marvel", stringResource(R.string.lorem_impsu), "", true))
}

@Composable
fun DescriptionModule(heroDescription: String) {

    val scrollState = rememberScrollState()

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)) {

        Text(text = heroDescription,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier.verticalScroll(scrollState))
    }
}

@Preview(showBackground = true)
@Composable
fun DescriptionModule_Preview() {
    DescriptionModule(stringResource(R.string.lorem_impsu))
}

@Composable
fun TabsForSeriesComics(series: List<Serie>, comics: List<Comic>) {

    var state by remember { mutableStateOf(0) }
    val titles = listOf("Series", "Comics")

    Column {
        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        if (state == 1){
            ComicsList(comics = comics)
        }else{
            SeriesList(series = series)
        }
    }
}

@Composable
fun ComicsList(comics: List<Comic>) {

    LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(comics, key = {it.id}) { comic ->
            ItemList(comic)
            DividerList()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComicsList_Preview() {
    ComicsList(
        listOf(
            Comic(123, "Un comic title", "Una descripcion", ""),
            Comic(1234, "Un comic title", "Una descripcion", ""),
            Comic(1235, "Un comic title", "Una descripcion", ""),
            Comic(1236, "Un comic title", "Una descripcion", ""),
        )
    )
}

@Composable
fun SeriesList(series: List<Serie>) {

    LazyColumn(Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(series, key = {it.id}) { serie ->
            ItemList(serie)
            DividerList()
        }
    }
}

@Composable
fun DividerList() {
    Divider(modifier = Modifier,
        thickness = DividerDefaults.Thickness,
        color = DividerDefaults.color)
}

@Preview(showBackground = true)
@Composable
fun SeriesList_Preview() {
    SeriesList(listOf(
        Serie(123, "Advengers", stringResource(R.string.lorem_impsu), 2010, 2014, ""),
        Serie(1234, "Advengers", stringResource(R.string.lorem_impsu), 2010, 2014, ""),
        Serie(12345, "Advengers", stringResource(R.string.lorem_impsu), 2010, 2014, ""),
    ))
}

@Composable
fun ItemList(item: MediaItem) {

    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = item.photo,
            contentDescription = "${item.photo} photo",
            modifier = Modifier
                .size(120.dp)
                .clip(RectangleShape),
            contentScale = ContentScale.Crop
        )
        Column(
             horizontalAlignment = Alignment.Start,
             verticalArrangement = Arrangement.Center,
             modifier = Modifier.fillMaxWidth())
        {
             Text(text = item.title,
                 style = MaterialTheme.typography.headlineSmall,
                 color = Color.Black
             )
             item.description?.let {
                 Text(text = it,
                     style =
                     MaterialTheme.typography.bodySmall,
                     color = Color.DarkGray,
                     modifier = Modifier.verticalScroll(scrollState)
                 )
             }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemList_Preview() {
    ItemList(Comic(123, "Algo", stringResource(R.string.lorem_impsu), "unafoto"))
}

@Composable
fun FavButton(favStatus: Boolean) {

    FloatingActionButton(
        containerColor = Rose,
        contentColor = RedWine,
        shape = CircleShape,
        onClick = {

        },
    ) {
        if (favStatus){
            Icon(Icons.Filled.Favorite, "Favorite Button")
        }else{
            Icon(Icons.Sharp.FavoriteBorder, "No favorite Button")
        }
    }
}

@Preview
@Composable
fun FavButton_Preview() {
    FavButton(false)
}








