package com.example.presentation.newsScreens.home


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.utils.model.NewsDTO


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel , onSearchClick : () -> Unit , onItemClicked : (String) -> Unit){
    val news by viewModel.newsList.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading
    Scaffold(
        modifier = Modifier.fillMaxSize() , topBar = {
            TopAppBar(
                title = {
                    Text(text = "NewLearnings")
                } ,
                actions = {
                    IconButton(onClick = {
                        onSearchClick()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search ,
                            contentDescription = null
                        )
                    }
                } ,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta ,
                    titleContentColor = Color.White ,
                    actionIconContentColor = Color.White
                )

            )
        }
    ){ ip  ->
        if(isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        else if(news.isNotEmpty() && news.first().title == "data not found") {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Something went wrong")
            }
        }
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(ip)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(news) { it ->
                        NewsCard(it){ id ->
                            onItemClicked(id)
                        }
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun NewsCard(newsDTO: NewsDTO = NewsDTO(title = "sjhjsdjs" , description = "jsskjsc") , onItemClicked : (String) -> Unit = {}){
        Card(
            modifier = Modifier.fillMaxWidth().clickable{
                onItemClicked(newsDTO.articleId)
            } , elevation = CardDefaults.cardElevation(4.dp)
        ){
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)){
                AsyncImage(
                    model = newsDTO.imageUrl ,contentDescription = null , modifier = Modifier
                        .padding(4.dp)
                        .size(120.dp)
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = newsDTO.title , fontSize = 20.sp , maxLines = 1 , overflow = TextOverflow.Clip)
                    Text(text = newsDTO.description , maxLines = 2 , overflow = TextOverflow.Ellipsis)
                    Text(text = if(newsDTO.language != null) newsDTO.language!! else "" , fontWeight = FontWeight.SemiBold)
                }
        }
    }
    Spacer(modifier = Modifier.padding(4.dp))
}