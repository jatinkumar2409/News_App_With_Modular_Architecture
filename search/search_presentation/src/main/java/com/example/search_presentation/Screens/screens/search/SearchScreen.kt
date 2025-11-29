package com.example.search_presentation.Screens.screens.search

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.utils.model.NewsDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewmodel : SearchScreenViewModel , onBack : () -> Unit , onItemClicked: (String) -> Unit){
    val searchList by viewmodel.searchList.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val isLoading by viewmodel.isLoading
    Scaffold(
        modifier = Modifier.fillMaxSize() , topBar = {
            TopAppBar(
                title = {
                    Text(text = "New Learnings")
                } ,
                navigationIcon = {
                    IconButton(onClick = {
                        onBack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack , contentDescription = "back")
                    }
                } , colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta ,
                    titleContentColor = Color.White ,
                   navigationIconContentColor = Color.White
                )

            )
        }
    ) { ip ->
        Column(
            modifier = Modifier.fillMaxSize().padding(ip)
        ){
            var search by remember() {
                mutableStateOf("")
            }
            val keyBoard = LocalSoftwareKeyboardController.current
            val focus = LocalFocusManager.current
            OutlinedTextField(value = search , onValueChange = {
                search = it
            } , maxLines = 1 , modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp) , placeholder = {
                Text(text = "Search")
            } , trailingIcon = {
                IconButton(onClick = {
                    if(search.trim().isNotEmpty()) {
                        viewmodel.getSearchData(search)
                        keyBoard?.hide()
                        focus.clearFocus(true)
                    }
                    else{
                        Toast.makeText(context, "Search block is empty", Toast.LENGTH_SHORT).show()
                    }

                }) {
                    Icon(imageVector = Icons.Default.Search , contentDescription = "search")
                }
            } , keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search , autoCorrect = true)
             , keyboardActions = KeyboardActions(onSearch = {
                    if(search.trim().isNotEmpty()) {
                        viewmodel.getSearchData(search)
                        keyBoard?.hide()
                        focus.clearFocus(true)
                    }
                    else{
                        Toast.makeText(context, "Search block is empty", Toast.LENGTH_SHORT).show()
                    }

                }))
            if(isLoading){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            else if(searchList.isNotEmpty() && searchList.first().title == ""){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                   Text(text = "Data not found")
                }
            }
            else{
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(searchList){
                        SearchCard(it) { id ->
                            onItemClicked(id)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun SearchCard(newsDTO: NewsDTO = NewsDTO(title = "sjhjsdjs" , description = "jsskjsc") , onItemClicked : (String) -> Unit = {}){
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