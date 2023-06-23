package com.example.entregasuperpoderesandroid.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.entregasuperpoderesandroid.data.model.Comic
import com.example.entregasuperpoderesandroid.R
import com.example.entregasuperpoderesandroid.data.model.Serie
import com.example.entregasuperpoderesandroid.data.model.SuperHeroCharacter
import com.example.entregasuperpoderesandroid.ui.theme.GreenDiff
import com.example.entregasuperpoderesandroid.ui.theme.RedWine
import com.example.entregasuperpoderesandroid.ui.viewModels.CharacterDetailViewModel

@Composable
fun SuperHeroCharacterDetailScreen(viewModel: CharacterDetailViewModel,
                                   characterID: Int) {

    val hero by viewModel.hero.collectAsState()
    val series by viewModel.series.collectAsState()
    val comics by viewModel.comics.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getHeroCharacter(characterID)
        viewModel.getComicsByHero(characterID)
        viewModel.getSeriesByHero(characterID)
    }
    hero?.let { SuperHeroCharacterDetailContent(it, series, comics, onSuperHeroFav = { id, favorite ->
        viewModel.markFavoriteHero(id, favorite)
    })
    }
}
@Composable
fun SuperHeroCharacterDetailContent(hero: SuperHeroCharacter,
                                    series: List<Serie>,
                                    comics: List<Comic>,
                                    onSuperHeroFav: (Int, Boolean) -> (Unit)) {

    Column(modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.End
        ) {

        HeadlineComponent(hero,onSuperHeroFav)
        DescriptionModule(hero.description)
        TabsForSeriesComics(series, comics)
    }
}

@Preview(showBackground = true)
@Composable
fun SuperHeroCharacterDetailContent_Preview() {
    SuperHeroCharacterDetailContent(
        SuperHeroCharacter(123, "Capitana Marvel", stringResource(R.string.lorem_impsu), "", true),
        listOf(
            Serie(123, "Advengers", stringResource(R.string.lorem_impsu), 2010, 2014, "", "listOf()"),
            Serie(1234, "Advengers", stringResource(R.string.lorem_impsu), 2010, 2014, "", "listOf()"),
            Serie(12345, "Advengers", stringResource(R.string.lorem_impsu), 2010, 2014, "", "listOf()"),
        ),
        listOf(
            Comic(123, "Un comic title", "Una descripcion", "",""),
            Comic(1234, "Un comic title", "Una descripcion", "","listOf()"),
            Comic(1235, "Un comic title", "Una descripcion", "", ""),
            Comic(1236, "Un comic title", "Una descripcion", "",""),
        ),{_,_ ->})
}

@Composable
fun HeadlineComponent(hero: SuperHeroCharacter, onSuperHeroFav: (Int, Boolean) -> (Unit)) {

    Row(
        modifier = Modifier
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
            style = MaterialTheme.typography.headlineSmall,
            color = Color.DarkGray,
            maxLines = 2,
            modifier = Modifier.weight(2f)
        )
        Box(
            modifier = Modifier.align(Alignment.Bottom)
        ) {
            FavButton(hero.favorite) {
                onSuperHeroFav(hero.id, !hero.favorite)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeadlineComponent_Preview() {
    HeadlineComponent(
        SuperHeroCharacter(
            123,
            "Capitana Marvel",
            stringResource(R.string.lorem_impsu),
            "",
            true),
             {_,_ ->})
}
@Composable
fun DescriptionModule(heroDescription: String) {

    val scrollState = rememberScrollState()

    if(heroDescription.length>1){

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Hero Description in detail"
            }) {

            Text(text = heroDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                maxLines = 5,
                modifier = Modifier.verticalScroll(scrollState))
        }
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
    val titles = listOf(stringResource(R.string.series), stringResource(R.string.series))

    Column {
        TabRow(
            selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    unselectedContentColor = Color.LightGray,
                    text = {
                        Text(text = title,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge) }
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
            Comic(123, "Un comic title", "Una descripcion", "", ""),
            Comic(1234, "Un comic title", "Una descripcion", "",""),
            Comic(1235, "Un comic title", "Una descripcion", "", ""),
            Comic(1236, "Un comic title", "Una descripcion", "", ""),
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
        Serie(123, "Advengers", stringResource(R.string.lorem_impsu), 2010, 2014, "", "listOf()"),
        Serie(1234, "Advengers", stringResource(R.string.lorem_impsu), 2010, 2014, "", "listOf()"),
        Serie(12345, "Advengers", stringResource(R.string.lorem_impsu), 2010, 2014, "", "listOf()"),
    ))
}

@Composable
fun ItemList(item: MediaItem) {

    val openDialog = remember { mutableStateOf(false)  }
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
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
             modifier = Modifier
                 .fillMaxWidth()
                 .fillMaxHeight())
        {
             Text(text = item.title,
                 style = MaterialTheme.typography.titleMedium,
                 color = Color.Black
             )
             item.description?.let {
                 Text(text = it,
                     style =
                     MaterialTheme.typography.bodySmall,
                     color = Color.DarkGray,
                     maxLines = 6,
                     modifier = Modifier.verticalScroll(scrollState)
                 )
             }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray
                ),
                shape = ShapeDefaults.Small,
                contentPadding = PaddingValues(1.dp),
                modifier = Modifier.padding(3.dp),
                onClick = {
                openDialog.value = true
            }) {
                Text(
                    text = stringResource(R.string.detalle_media_item),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.DarkGray
                )
            }
        }
    }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            containerColor = Color.White,
            titleContentColor = Color.Black,
            textContentColor = Color.DarkGray,
            title = {
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    text = "${item.title},  ${stringResource(R.string.stories)}"
                )
            },
            text = {
                Text(
                    style =  MaterialTheme.typography.bodyMedium,
                    text = item.storyName)
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    shape = ShapeDefaults.Small,
                    contentPadding = PaddingValues(1.dp),
                    modifier = Modifier.padding(3.dp),
                    onClick = {
                        openDialog.value = false
                    }) {
                    Icon(imageVector = Icons.Filled.Check,
                        contentDescription = stringResource(R.string.cerrar),
                        tint = GreenDiff,
                        modifier = Modifier.size(25.dp))
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemList_Preview() {
    ItemList(Comic(123, "Algo", stringResource(R.string.lorem_impsu), "unafoto", "listOf()"))
}
@Composable
fun FavButton(fav: Boolean, clickFav: () -> Unit) {

    FloatingActionButton(
        containerColor = Color.Transparent,
        contentColor = RedWine,
        elevation = FloatingActionButtonDefaults.elevation(1.dp),
        shape = CircleShape,
        onClick = clickFav,
        modifier = Modifier.size(50.dp)
    ) {
        if (fav){
            Icon(Icons.Filled.Favorite, stringResource(R.string.favorite_button))
        }else{
            Icon(Icons.Sharp.FavoriteBorder, stringResource(R.string.no_favorite_button))
        }
    }
}

@Preview
@Composable
fun FavButton_Preview() {
    FavButton(true, {})
}








