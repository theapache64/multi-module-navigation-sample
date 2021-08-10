package com.sample.multimodulenavigation.commoncore

class TabSet {
    private val tabs = mutableListOf<DashboardScreen>()

    fun add(tabScreen: DashboardScreen) {
        tabs.remove(tabScreen) // remove if exist
        tabs.add(tabScreen) // add to the end
    }

    fun remove(route: String) {
        val index = tabs.indexOfFirst { it.route == route }
        if (index != -1) {
            tabs.removeAt(index)
        }
    }

    fun lastOrNull(): DashboardScreen? {
        return tabs.lastOrNull()
    }
}