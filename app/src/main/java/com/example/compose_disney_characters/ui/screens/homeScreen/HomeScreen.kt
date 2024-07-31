package com.example.compose_disney_characters.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeAction
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeState
import com.example.compose_disney_characters.ui.theme.Compose_disney_charactersTheme
import com.example.compose_disney_characters.R
import com.example.compose_disney_characters.models.CharacterItemModel
import com.example.compose_disney_characters.ui.navigation.ScreenRoute
import com.example.compose_disney_characters.ui.theme.Background
import com.example.compose_disney_characters.ui.theme.Secondary

@Composable
fun HomeDestination(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.observeAsState()
    HomeScreen(state = state.value, viewModel::processAction) { id ->
        navHostController.navigate(ScreenRoute.Details.selectRoute(id))
    }
}

@Composable
fun HomeScreen(
    state: HomeState?,
    processAction: (action: HomeAction) -> Unit,
    onClick: (id: Int) -> Unit
) {
    processAction(HomeAction.Init)

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Background)
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(
                        items = state?.disneyCharactersList ?: arrayListOf()
                    ) { item ->
                        Column(
                            modifier = Modifier
                                .background(Background)
                                .padding(10.dp)
                                .clickable { onClick(item.id) }
                        ) {
                            Card(
                                shape = RoundedCornerShape(size = 20.dp),
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(170.dp)
                            ) {
                                AsyncImage(
                                    model = item.imageUrl,
                                    placeholder = painterResource(id = R.drawable.no_heroes_here),
                                    error = painterResource(id = R.drawable.no_heroes_here),
                                    contentDescription = stringResource(id = R.string.character_image),
                                    modifier = Modifier
                                        .height(150.dp)
                                        .width(170.dp)
                                        .background(Secondary),
                                )
                            }
                            Text(
                                text = item.name,
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                }
                if (state?.isLoading == true) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(50.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Compose_disney_charactersTheme {
        HomeScreen(
            state = HomeState(
                isLoading = true,
                listOf(
                    CharacterItemModel(
                        1,
                        "Herro1",
                        ""
                    ),
                    CharacterItemModel(
                        1,
                        "Herro2",
                        ""
                    ),
                    CharacterItemModel(
                        1,
                        "Herro3",
                        ""
                    ),
                    CharacterItemModel(
                        1,
                        "Herro4",
                        ""
                    )
                )
            ),
            { },
            { }
        )
    }
}