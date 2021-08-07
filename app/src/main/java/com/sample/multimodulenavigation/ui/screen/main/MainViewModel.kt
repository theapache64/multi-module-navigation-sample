package com.sample.multimodulenavigation.ui.screen.main

import androidx.lifecycle.ViewModel
import com.sample.multimodulenavigation.dashboard.Tab
import com.sample.multimodulenavigation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _tabs = MutableStateFlow<Resource<List<Tab>>>(Resource.Idle())
    val tabs = _tabs.asStateFlow()

    private val _shouldShowTabs = MutableStateFlow(false)
    val shouldShowTabs = _shouldShowTabs.asStateFlow()
}