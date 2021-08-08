package com.sample.multimodulenavigation.commoncore

class TabSet {
    private val tabs = mutableListOf<LeafScreen>()

    fun add(tabScreen: LeafScreen) {
        tabs.remove(tabScreen) // remove if exist
        tabs.add(tabScreen)
    }

    fun remove(tabScreen: LeafScreen) {
        tabs.remove(tabScreen)
    }
}