package com.sample.multimodulenavigation.commoncore

class TabSet {
    private val tabs = mutableListOf<String>()

    fun bringToFront(tabId: String) {
        tabs.remove(tabId) // remove if exist
        tabs.add(tabId) // add to the end
    }

    fun remove(tabId: String) {
        val index = tabs.indexOfFirst { it == tabId }
        if (index != -1) {
            tabs.removeAt(index)
        }
    }

    fun lastOrNull(): String? {
        return tabs.lastOrNull()
    }

    fun contains(tabId: String): Boolean {
        return tabs.indexOfFirst { it == tabId } != -1
    }
}