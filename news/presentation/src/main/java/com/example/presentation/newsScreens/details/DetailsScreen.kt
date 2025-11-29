package com.example.presentation.newsScreens.details

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.presentation.newsScreens.DetailsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(id : String , viewModel: DetailsScreenViewModel ,onBack : () -> Unit) {
    val news by viewModel.news
    val isLoading by viewModel.isLoading
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "NewLearnings")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )

        }
    ) { ip ->
        Spacer(modifier = Modifier.height(8.dp))
        LaunchedEffect(Unit) {
            viewModel.getNewsData(id)
        }
        Log.d("tag", "DetailsScreen: $news")
        if (isLoading || news == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (news != null && news?.title == "Data not found") {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Something went wrong")
            }
        } else {
            Log.d("tag", "DetailsScreen: $news")
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(ip) ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = news?.imageUrl,
                    contentDescription = "image",
                    modifier = Modifier.size(240.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = news?.title ?: "", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = news?.description ?: "", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = news?.language ?: "",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}