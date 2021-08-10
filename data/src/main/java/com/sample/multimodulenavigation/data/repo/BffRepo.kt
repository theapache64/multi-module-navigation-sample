package com.sample.multimodulenavigation.data.repo

import com.sample.multimodulenavigation.data.model.*
import kotlinx.coroutines.delay
import javax.inject.Inject

class BffRepo @Inject constructor() {

    companion object {
        private const val SPACE_TYPE_TRAY = "tray"
        private const val SPACE_TYPE_BOTTOM_NAVIGATION = "bottom_navigation"
    }

    suspend fun getPage(pageId: String): BffResponse {

        delay(2000)
        return BffResponse(
            isSuccess = true,
            data = BffData(
                page = Page(
                    pageType = "landing",
                    spaces = listOf(
                        createTraySpace(),
                        createMenuSpace()
                    )
                )
            )
        )
    }

    private fun createMenuSpace(): Space {

        return Space(
            spaceId = "space_id_menu",
            spaceType = SPACE_TYPE_BOTTOM_NAVIGATION,
            data = SpaceData(
                nextPage = "/v1/pages/{page_id}/spaces/{space_id}?offset=10",
                maxWidgets = 10,
                minWidgets = 5
            ),
            widgets = listOf(
                Widget(
                    template = Template("tab", version = "1.0.0"),
                    data = null,
                    pagination = null,
                    widgetItems = createMenuItems()
                )
            )
        )
    }

    private fun createMenuItems(): List<WidgetItem<TabData>> {
        return mutableListOf<WidgetItem<TabData>>().apply {
            repeat(5) { idx ->
                add(
                    WidgetItem(
                        template = Template("tab_item", version = "1.0.0"),
                        data = TabData(
                            "tab_$idx",
                            "page_$idx",
                            "Menu $idx"
                        )
                    )
                )
            }
        }
    }

    private fun createTraySpace(): Space {
        return Space(
            spaceId = "space_id_tray",
            spaceType = SPACE_TYPE_TRAY,
            data = SpaceData(
                nextPage = "/v1/pages/{page_id}/spaces/{space_id}?offset=10",
                maxWidgets = 10,
                minWidgets = 5
            ),
            widgets = createMovieWidgets()
        )
    }

    private fun createMovieWidgets(): List<Widget> {
        return mutableListOf<Widget>().apply {
            repeat(10) {
                add(
                    Widget(
                        template = Template("tab", version = "1.0.0"),
                        data = null,
                        pagination = null,
                        widgetItems = createMovieWidgetItems()
                    )
                )
            }
        }
    }

    private fun createMovieWidgetItems(): List<WidgetItem<PosterData>> {
        return mutableListOf<WidgetItem<PosterData>>().apply {
            repeat(100) { idx ->
                add(
                    WidgetItem<PosterData>(
                        template = Template("tab_item", version = "1.0.0"),
                        data = PosterData(
                            "movies_$idx"
                        )
                    )
                )
            }
        }
    }
}
