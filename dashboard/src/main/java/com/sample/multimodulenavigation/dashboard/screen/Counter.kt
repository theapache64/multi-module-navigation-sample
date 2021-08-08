package com.sample.multimodulenavigation.dashboard.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@Composable
fun Counter(
    title: String,
    onShowTabsClicked: () -> Unit,
    onHideTabsClicked: () -> Unit,
    onSubmit: (String, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CounterViewModel = hiltViewModel()
) {
    val count by viewModel.count.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = title, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(30.dp))

        Row {
            Button(onClick = { viewModel.onDecrementClicked() }) {
                Text(text = "-")
            }

            Text(
                text = "$count",
                fontSize = 30.sp,
                modifier = Modifier.padding(horizontal = 30.dp)
            )

            Button(onClick = { viewModel.onIncrementClicked() }) {
                Text(text = "+")
            }

        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { onSubmit(title, count) },
        ) {
            Text(text = "SUBMIT")
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row {
            Button(onClick = { onShowTabsClicked() }) {
                Text(text = "SHOW TABS")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(onClick = { onHideTabsClicked() }) {
                Text(text = "HIDE TABS")
            }
        }
    }
}

@HiltViewModel
class CounterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        println("Creating new viewModel $savedStateHandle -> $this")
    }

    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    fun onIncrementClicked() {
        _count.value = _count.value + 1
    }

    fun onDecrementClicked() {
        _count.value = _count.value - 1
    }

}