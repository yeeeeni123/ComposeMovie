package com.yeen.main.home

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.yeen.data.response.MovieItem
import com.yeen.data.response.MovieResponse
import com.yeen.login.LoginEvent
import com.yeen.main.R

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        Column(
            Modifier
                .padding(5.dp, 10.dp, 10.dp, 0.dp)
                .fillMaxWidth()
        ) {
            Text(text = "무비차트",
                fontSize = 14.sp, fontWeight = FontWeight.Bold,
                style = androidx.compose.ui.text.TextStyle(
                    textAlign = TextAlign.Left
                ),
                modifier = Modifier
                    .padding(5.dp, 10.dp, 10.dp, 10.dp)
                    .fillMaxWidth()
            )
            MovieListView(viewModel)
        }
    }
}


@Composable
fun MovieListView(movieVM: HomeViewModel = hiltViewModel()){
    // collectAsState() - flow 값 변화 감지 근데 state 이므로 값이 들어오면, 리컴포지션에 의해 뷰가 다시 그려짐
    val movies by movieVM.moviesFlow.collectAsState()
    if(movies.isEmpty()){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    } else {
        LazyRow(){
            items(movies){
                MovieView(data = it)
            }
        }
    }
}

@Composable
fun MovieView(data: MovieItem){
        Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.brightGray),),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileImage(imageUrl = "https://image.tmdb.org/t/p/w500/${data.poster_path}")
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = data.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = data.vote_average.toString()+"% 관객: "+data.vote_count.toString(),
                fontSize = 10.sp)

            var showSheet by remember { mutableStateOf(false) }
            if (showSheet) {
                BottomSheet() {
                    showSheet = false
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .height(30.dp)
                    .width(70.dp)
                    .border(width = 1.dp,
                        color = colorResource(id = R.color.brightRed),
                        shape = RoundedCornerShape(20.dp))
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        showSheet = true
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("예매", color = colorResource(id = R.color.brightRed), fontSize = 14.sp)
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
//        CountryList()
    }
}


@Composable
fun ProfileImage(imageUrl: String, modifier: Modifier = Modifier){
    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }
    val imageModifier = modifier
        .size(150.dp, 120.dp)
        .clip(RoundedCornerShape(5.dp))

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imageUrl)
        .into(object : CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    bitmap.value?.asImageBitmap()?.let {
        Image(
            bitmap = it,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
    } ?: Image(
        painter = painterResource(id = R.drawable.ticket_list),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = imageModifier
    )
}
