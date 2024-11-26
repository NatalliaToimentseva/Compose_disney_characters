package com.example.compose_disney_characters.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.compose_disney_characters.R
import com.example.compose_disney_characters.models.CharacterFieldsModel
import com.example.compose_disney_characters.models.CharacterMainData
import com.example.compose_disney_characters.ui.navigation.ScreenRoute
import com.example.compose_disney_characters.ui.screens.details.domain.DetailsAction
import com.example.compose_disney_characters.ui.screens.details.domain.DetailsState
import com.example.compose_disney_characters.ui.theme.Background
import com.example.compose_disney_characters.ui.theme.Compose_disney_charactersTheme
import com.example.compose_disney_characters.ui.theme.Secondary
import com.example.compose_disney_characters.utils.toast

@Composable
fun DetailsDestination(
    id: Int,
    navHostController: NavHostController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.observeAsState()
    DetailsScreen(id = id, state = state, viewModel::processAction) {
        navHostController.popBackStack(route = ScreenRoute.Home.route, inclusive = false)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsScreen(
    id: Int,
    state: DetailsState?,
    processAction: (action: DetailsAction) -> Unit,
    goBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        processAction(DetailsAction.Init(id))
    }

    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .background(Background)
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(290.dp)
                            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                            .background(Secondary)
                    ) {
                        AsyncImage(
                            model = state?.character?.imgUrl,
                            placeholder = painterResource(id = R.drawable.no_heroes_here),
                            error = painterResource(id = R.drawable.no_heroes_here),
                            contentDescription = stringResource(id = R.string.character_image),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize()
                        )
                        IconButton(
                            onClick = { goBack() },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(20.dp, 40.dp, 0.dp, 0.dp)
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.back_btn),
                                contentDescription = stringResource(id = R.string.back_button),
                            )
                        }
                    }
                    Text(
                        text = state?.character?.name
                            ?: stringResource(id = R.string.error_loading_data),
                        fontSize = 30.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(10.dp, 10.dp, 0.dp, 0.dp)

                    )
                    LazyColumn(contentPadding = PaddingValues(10.dp)) {
                        items(
                            items = state?.character?.fields ?: arrayListOf()
                        ) { itemField ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) {
                                Text(
                                    text = itemField.name,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(5.dp)
                                )
                                FlowRow(modifier = Modifier.padding(5.dp)) {
                                    itemField.movies.forEach { itemsValue ->
                                        Box(
                                            modifier = Modifier
                                                .wrapContentSize()
                                                .padding(5.dp)
                                                .clip(shape = RoundedCornerShape(15.dp))
                                                .background(Secondary)
                                        ) {
                                            Text(
                                                text = itemsValue,
                                                fontSize = 14.sp,
                                                color = Color.White,
                                                modifier = Modifier.padding(
                                                    20.dp,
                                                    2.dp,
                                                    20.dp,
                                                    2.dp
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (state?.isInProgress == true) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(50.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }

                if (state?.error != null) {
                    LocalContext.current.toast(state.error)
                    processAction(DetailsAction.ClearError)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    Compose_disney_charactersTheme {
        DetailsScreen(
            id = 1,
            state = DetailsState(
                isInProgress = true,
                character = CharacterMainData(
                    name = "Queen Arianna",
                    imgUrl = "https://static.wikia.nocookie.net/disney/images/1/15/Arianna_Tangled.jpg/revision/latest?cb=20160715191802",
                    fields = listOf(
                        CharacterFieldsModel(
                            name = "films",
                            movies = listOf("Tangled", "Tangled: Before Ever After")
                        ),
                        CharacterFieldsModel(
                            name = "tvShows",
                            movies = listOf("Once Upon a Time", "Tangled: The Series")
                        ),
                    )
                )
            ),
            { },
            { }
        )
    }
}
