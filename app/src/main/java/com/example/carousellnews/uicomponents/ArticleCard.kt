package com.example.carousellnews.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.carousellnews.data.Data
import java.time.Duration
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Composable
fun ArticleCard(data: Data) {

    Card(modifier = Modifier.padding(8.dp).fillMaxWidth(),
    elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(data.image),
                contentDescription = data.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .shadow(elevation = 2.dp, shape = MaterialTheme.shapes.medium)
            )
            Text(
                text = data.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFF262629),
                modifier = Modifier.padding(top = 8.dp).width(312.dp).wrapContentHeight()
            )
            Text(
                text = data.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                color = Color(0xFF262629),
                modifier = Modifier.padding(top = 4.dp).width(312.dp).wrapContentHeight()
            )
            Text(
                text = formatedTime(data.time),
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                color = Color(0xFF8F939C),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


private fun formatedTime(time: Long): String {
    try {
        val past = Instant.ofEpochSecond(time).atOffset(ZoneOffset.UTC)
        val now = OffsetDateTime.now()
        val days = Duration.between(past, now).toDays()
        val hours = Duration.between(past, now).toHours()
        return when {
            hours < 24 -> "$hours hour${if (hours != 1L) "s" else ""} ago"
            days < 7 -> "$days day${if (days != 1L) "s" else ""} ago"
            days < 30 -> "${days / 7} week${if (days / 7 != 1L) "s" else ""} ago"
            days < 365 -> "${days / 30} month${if (days / 30 != 1L) "s" else ""} ago"
            else -> "${days / 365} year${if (days / 365 != 1L) "s" else ""} ago"
        }
    } catch (e: Exception) {
        return "Unknown"
    }
}

