package org.nextoptech.news.ui.news_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import org.nextoptech.news.common.AppConstant.BULLET
import org.nextoptech.news.common.toJson
import org.nextoptech.news.data.data_source.local.NewsEntity
import org.nextoptech.news.utils.QueryParams

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun NewsListItem(
    news: NewsEntity,
    onNewsClick: (String) -> Unit
) {


    val configuration = LocalConfiguration.current
    val heightInDp = configuration.screenHeightDp.dp


    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)


    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(heightInDp / 4)
            .clickable {
                val articleString = news.toJson()
                if (articleString != null) {
                    onNewsClick(articleString)
                }
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        ) {

            Card(
                modifier = Modifier
                    .weight(.5f)
                    .fillMaxWidth()
                    .padding(0.dp)
                    .clickable { },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(0.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 16.dp
                )
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    GlideImage(
                        model = news.imageUrl,
                        contentDescription = news.sourceName.toString(),
                        contentScale = ContentScale.FillBounds,
                        alpha = .7f,
                        modifier = Modifier
                            .background(color = Color.Gray)
                            .fillMaxWidth()
                            .height(heightInDp / 4)
                            .drawWithCache {
                                val gradient = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = size.height / 3,
                                    endY = size.height
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(gradient, blendMode = BlendMode.Multiply)
                                }
                            }
                            .clickable {
                                val articleString = news.toJson()
                                if (articleString != null) {
                                    onNewsClick(articleString)
                                }
                            },
                    )


                    Text(
                        text = news.title!!,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.BottomStart),
                        maxLines = 3,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            }


            Column(
                modifier = Modifier
                    .weight(.5f)
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {


                Text(
                    text = news.content.toString(),
                    modifier = Modifier.padding(bottom = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.DarkGray
                )



                HorizontalDivider(
                    thickness = .5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp)
                        .background(Color.DarkGray)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Text(
                        text = news.queryName.toString(),
                        maxLines = 1,
                        modifier = Modifier
                            .weight(.5f)
                            .basicMarquee(),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = " $BULLET ",
                    )
                    if (news.publishedAt != null) {
                        Text(
                            text = news.publishedAt.toString(),
                            modifier = Modifier
                                .weight(.5f),
                            maxLines = 1,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNewsListItem() {
    NewsListItem(
        news = NewsEntity(
            "author",
            "content", "description",
            "PublishedAt", "title", "url", "imageUrl", "SourceName", "SourceId", QueryParams.Google
        ),
    ) {

    }
}
