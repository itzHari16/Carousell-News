package com.example.carousellnews.uicomponents

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carousellnews.viewmodel.SortType
import com.example.carousellnews.viewmodel.ViewModel

@Composable
fun MainScreen(viewModel: ViewModel = hiltViewModel()) {
    val articles by viewModel.news.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar{
                 onSort -> viewModel.sort(onSort) }

        },
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(articles) { item ->
                ArticleCard(item)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    onSort: (SortType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Carousell News", color = Color.White, fontSize = 16.sp) },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Menu", tint = Color.White)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    text = { Text("Recent", color = Color.Black) },
                    onClick = {
                        expanded = false
                        onSort(SortType.Recent)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Popular", color = Color.Black) },
                    onClick = {
                        expanded = false
                        onSort(SortType.Popular)
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFCC0000))
    )
}

