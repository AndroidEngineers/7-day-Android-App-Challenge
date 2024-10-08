package com.example.quotesapp.ui.home_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aghajari.compose.lazyswipecards.LazySwipeCards
import com.example.quotesapp.R
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.ui.home_screen.util.QuoteEvent
import com.example.quotesapp.ui.theme.GIFont
import com.example.quotesapp.ui.theme.customBlack
import com.example.quotesapp.ui.theme.customGrey
import com.example.quotesapp.ui.viewmodel.QuoteViewModel

@Composable
fun QuoteItem(data: Quote, quoteViewModel: QuoteViewModel){

    val context = LocalContext.current
    val state = quoteViewModel.quoteState.value

    val gradient = Brush.radialGradient(
        0.0f to customBlack,
        1.0f to customGrey,
        radius = 1000.0f,
        tileMode = TileMode.Repeated
    )

    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(gradient)
            .fillMaxSize()){

        AsyncImage(
            model = R.drawable.quotation,
            contentDescription = null,
            modifier= Modifier
                .align(Alignment.TopStart)
                .padding(start=12.dp,top = 15.dp)
                .size(30.dp))

        Column(modifier = Modifier.wrapContentSize()
            .background(Color.Transparent)
            .align(Alignment.Center)
        ) {
            Text(text = data.quote,
                fontFamily = GIFont,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp),
                color = Color.White,
                style = TextStyle(
                    lineHeight = 40.sp // Set the line height
                )
            )

            Spacer(modifier= Modifier.height(50.dp))

            Text(text = data.author,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 15.dp))
        }

        Column(modifier= Modifier.wrapContentSize()
            .align(Alignment.BottomEnd)
            .padding(horizontal = 20.dp,vertical=28.dp)) {

            if (data.liked){

                AsyncImage(model = R.drawable.heart_filled,
                    contentDescription = null,
                    modifier= Modifier.size(35.dp)
                        .clickable {
                            quoteViewModel.onEvent(QuoteEvent.Like(data))
                        })
            }else{
                AsyncImage(model = R.drawable.heart_unfilled,
                    contentDescription = null,
                    modifier= Modifier.size(35.dp)
                        .clickable {
                            quoteViewModel.onEvent(QuoteEvent.Like(data))
                        })

            }
            Spacer(modifier= Modifier.height(25.dp))

            AsyncImage(model = R.drawable.send,
                contentDescription = null,
                modifier= Modifier.size(35.dp).clickable {
//                    quoteViewModel.onEvent(QuoteEvent.SHARE)
                    Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show()
                })
        }
    }
}

@Composable
fun QuoteItemListSection( quoteViewModel: QuoteViewModel) {

    val state = quoteViewModel.quoteState.value

    LazySwipeCards(cardColor = Color.Transparent,
        cardShadowElevation = 0.dp,
        translateSize = 8.dp,
        swipeThreshold = 0.4f) {

        //

        Log.d("TAG","above items")

            items(state.dataList) {it->
                Log.d("TAG","FROM ITEMS ${state.dataList.size}")
                Log.d("TAG","FROM ITEMS ${it.quote}")
                QuoteItem(it,quoteViewModel)

            }
            onSwiped { item, _ ->
                state.dataList.add(item as Quote)
            }
        }
    }

