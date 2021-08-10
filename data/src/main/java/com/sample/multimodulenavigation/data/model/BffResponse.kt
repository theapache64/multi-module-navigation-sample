package com.sample.multimodulenavigation.data.model

data class BffResponse(
    val isSuccess: Boolean,
    val data: BffData
)

data class BffData(
    val page: Page
)

data class Page(
    val pageType: String,
    val spaces: List<Space>
)

data class Space(
    val spaceId: String,
    val spaceType: String,
    val data: SpaceData,
    val widgets: List<Widget>
)

class SpaceData(
    val nextPage: String,
    val maxWidgets: Int,
    val minWidgets: Int
)

data class Widget(
    val template: Template,
    val data: WidgetData?,
    val pagination: Pagination?,
    val widgetItems: List<WidgetItem<*>>?
)

data class Template(
    val id: String,
    val version: String
)

data class WidgetData(
    val title: String?
)

data class Pagination(
    val nextPageUrl: String
)

open class WidgetItemData

data class WidgetItem<T : WidgetItemData>(
    val template: Template,
    val data: T
)

data class TabData(
    val id: String,
    val pageId: String,
    val title: String
) : WidgetItemData()

data class PosterData(
    val id: String
) : WidgetItemData()