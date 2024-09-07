package org.nextoptech.news.ui.news_details

import android.content.res.ColorStateList
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.util.LinkifyCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.nextoptech.news.R
import org.nextoptech.news.data.data_source.local.NewsEntity


@Composable
fun NewsDetailsScreenRoute(news: NewsEntity) {

    val newsDetailsViewModel: NewsDetailsViewModel = hiltViewModel()

    NewsDetailsScreen(
        news = news,
        onParseHtml = newsDetailsViewModel::parseHtml
    )
}



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetailsScreen(
    news: NewsEntity?,
    onParseHtml: (String) -> String
) {


    val states = arrayOf(
        intArrayOf(android.R.attr.state_enabled),
        intArrayOf(-android.R.attr.state_enabled),
        intArrayOf(android.R.attr.state_pressed)
    )

    val colors = intArrayOf(
        Color.Black.toArgb(), Color.Red.toArgb(), Color.Blue.toArgb()
    )


    val mContext = LocalContext.current
    val mCustomLinkifyText = remember { TextView(mContext) }

    Scaffold { contentPadding ->
        Box(modifier= Modifier.padding(contentPadding)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                GlideImage(
                    model = news?.imageUrl,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                )
                {
                    it.error(R.drawable.placeholder).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                }
            }
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(color = Color.Gray.copy(alpha = .6f))
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start

            ) {

                Text(
                    text = news?.title!!,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Text(
                    text = "${news.author.toString()}  •  ${
                        news.publishedAt.toString()
                    } ",
                    modifier = Modifier.padding(8.dp),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )

                HorizontalDivider(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.DarkGray)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_newspaper_24),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp),
                        tint = Color.White
                    )


                    Text(
                        text = news.sourceName.toString(),
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )


                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Text(
                        text = onParseHtml(news.description.toString()),
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )

                }

                Text(
                    text = onParseHtml(news.content!!.toString()),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

                AndroidView(
                    factory = { mCustomLinkifyText },
                    modifier = Modifier.padding(
                        bottom = 8.dp,
                        start = 8.dp,
                        end = 8.dp,
                        top = 16.dp
                    )
                ) { textView ->
                    textView.text = news.url
                    textView.textSize = 14F
                    textView.setTextColor(Color.Blue.toArgb())
                    textView.setTextColor(ColorStateList(states, colors))
                    LinkifyCompat.addLinks(textView, Linkify.ALL)
                    textView.movementMethod = LinkMovementMethod.getInstance()
                }


            }
        }
    }

}

