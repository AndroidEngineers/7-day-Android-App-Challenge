package com.abhijith.animex.data.network.model

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(

    @SerializedName("pagination")
    val pagination: PaginationDto? = null,

    @SerializedName("data")
    val data: List<AnimeDto?>? = null // can be null in case we reach the end of data
)

data class PaginationDto(

    @SerializedName("has_next_page")
    val hasNextPage: Boolean? = null,

    @SerializedName("last_visible_page")
    val lastVisiblePage: Int? = null,

    @SerializedName("items")
    val items: PaginationItemsDto? = null,

    @SerializedName("current_page")
    val currentPage: Int? = null
)

data class PaginationItemsDto(

    @SerializedName("per_page")
    val perPage: Int? = null,

    @SerializedName("total")
    val total: Int? = null,

    @SerializedName("count")
    val count: Int? = null
)
