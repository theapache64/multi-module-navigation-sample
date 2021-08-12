package com.sample.multimodulenavigation.dashboard.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.multimodulenavigation.commoncore.Resource
import com.sample.multimodulenavigation.dashboard.Tab
import com.sample.multimodulenavigation.data.model.*
import com.sample.multimodulenavigation.data.repo.BffRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@Composable
fun PageScreen(
    viewModel: PageViewModel = hiltViewModel(),
    onTabsLoaded: (List<Tab>) -> Unit
) {
    val bffResponse by viewModel.bffResponse.collectAsState()
    val pageId by viewModel.pageId.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (bffResponse) {
            is Resource.Loading -> {
                PageLoading(pageId)
            }
            is Resource.Success -> {
                Column {
                    Text(text = "PageId : '$pageId'", fontSize = 20.sp)

                    Box {

                        for (space in (bffResponse as Resource.Success<BffResponse>).data.data.page.spaces) {
                            when (space.spaceType) {
                                BffRepo.SPACE_TYPE_TRAY -> {
                                    Tray(space.widgets)
                                }

                                BffRepo.SPACE_TYPE_OVERLAY -> {
                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(Color.Red)
                                            .align(Alignment.Center)
                                    )
                                }

                                BffRepo.SPACE_TYPE_BOTTOM_NAVIGATION -> {
                                    val tabs = space.widgets[0].widgetItems!!.map {
                                        val data = it.data as TabData
                                        Tab(
                                            data.id,
                                            data.pageId,
                                            data.title,
                                            Icons.Outlined.Warning
                                        )
                                    }
                                    onTabsLoaded(tabs)
                                }
                            }
                        }
                    }
                }
            }
            is Resource.Error -> TODO()
            is Resource.Idle -> TODO()
        }
    }
}

@Composable
fun Tray(widgets: List<Widget>) {
    LazyColumn {
        for (widget in widgets) {
            item {
                Column(
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text(text = widget.data?.title ?: "Title missing")

                    LazyRow {
                        if (widget.template.id == BffRepo.WIDGET_ITEM_NORMAL_POSTER) {
                            items(widget.widgetItems as List<WidgetItem<PosterData>>) {
                                Poster(it)
                            }
                        } else {
                            throw IllegalArgumentException("mapping not found for ${widget.template.id}")
                        }
                    }

                }


            }
        }
    }
}

@Composable
private fun Poster(it: WidgetItem<PosterData>) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(50.dp)
            .padding(end = 10.dp)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(text = it.data.id)
    }
}

@Composable
private fun PageLoading(
    pageId: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Loading '$pageId'", fontSize = 20.sp)
    }
}

@HiltViewModel
class PageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    bffRepo: BffRepo
) : ViewModel() {

    private val _pageId = MutableStateFlow("")
    val pageId = _pageId.asStateFlow()

    private val _bffResponse = MutableStateFlow<Resource<BffResponse>>(Resource.Loading())
    val bffResponse = _bffResponse.asStateFlow()

    init {
        _pageId.value = savedStateHandle.get<String>("pageId")!!

        viewModelScope.launch {
            _bffResponse.value = Resource.Loading()
            _bffResponse.value = Resource.Success(bffRepo.getPage(pageId.value))
        }
    }
}