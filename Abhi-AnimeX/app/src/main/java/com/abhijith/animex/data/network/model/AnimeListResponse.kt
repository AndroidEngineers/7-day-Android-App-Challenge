package com.abhijith.animex.data.network.model

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(

    @SerializedName("pagination")
    val pagination: Pagination? = null,

    @SerializedName("data")
    val data: List<AnimeDto?>? = null // can be null in case we reach the end of data
)

data class Aired(

    @SerializedName("string")
    val string: String? = null,

    @SerializedName("prop")
    val prop: Prop? = null,

    @SerializedName("from")
    val from: String? = null,

    @SerializedName("to")
    val to: Any? = null
)

data class Prop(

    @SerializedName("from")
    val from: From? = null,

    @SerializedName("to")
    val to: To? = null
)

data class LicensorsItem(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("mal_id")
    val malId: Int? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("url")
    val url: String? = null
)

data class Images(

    @SerializedName("jpg")
    val jpg: Jpg? = null,

    @SerializedName("webp")
    val webp: Webp? = null,

    @SerializedName("large_image_url")
    val largeImageUrl: String? = null,

    @SerializedName("small_image_url")
    val smallImageUrl: String? = null,

    @SerializedName("image_url")
    val imageUrl: String? = null,

    @SerializedName("medium_image_url")
    val mediumImageUrl: String? = null,

    @SerializedName("maximum_image_url")
    val maximumImageUrl: String? = null
)

data class ProducersItem(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("mal_id")
    val malId: Int? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("url")
    val url: String? = null
)

data class Trailer(

    @SerializedName("images")
    val images: Images? = null,

    @SerializedName("embed_url")
    val embedUrl: String? = null,

    @SerializedName("youtube_id")
    val youtubeId: String? = null,

    @SerializedName("url")
    val url: String? = null
)

data class AnimeDto(

    @SerializedName("title_japanese")
    val titleJapanese: String? = null,

    @SerializedName("favorites")
    val favorites: Int? = null,

    @SerializedName("broadcast")
    val broadcast: Broadcast? = null,

    @SerializedName("year")
    val year: Int? = null,

    @SerializedName("rating")
    val rating: String? = null,

    @SerializedName("scored_by")
    val scoredBy: Int? = null,

    @SerializedName("title_synonyms")
    val titleSynonyms: List<String?>? = null,

    @SerializedName("source")
    val source: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("trailer")
    val trailer: Trailer? = null,

    @SerializedName("duration")
    val duration: String? = null,

    @SerializedName("score")
    val score: Double? = null,

    @SerializedName("themes")
    val themes: List<ThemesItem?>? = null,

    @SerializedName("approved")
    val approved: Boolean? = null,

    @SerializedName("genres")
    val genres: List<GenresItem?>? = null,

    @SerializedName("popularity")
    val popularity: Int? = null,

    @SerializedName("members")
    val members: Int? = null,

    @SerializedName("title_english")
    val titleEnglish: String? = null,

    @SerializedName("rank")
    val rank: Int? = null,

    @SerializedName("season")
    val season: String? = null,

    @SerializedName("airing")
    val airing: Boolean? = null,

    @SerializedName("episodes")
    val episodes: Int? = null,

    @SerializedName("aired")
    val aired: Aired? = null,

    @SerializedName("images")
    val images: Images? = null,

    @SerializedName("studios")
    val studios: List<StudiosItem?>? = null,

    @SerializedName("mal_id")
    val malId: Int? = null,

    @SerializedName("titles")
    val titles: List<TitlesItem?>? = null,

    @SerializedName("synopsis")
    val synopsis: String? = null,

    @SerializedName("explicit_genres")
    val explicitGenres: List<Any?>? = null,

    @SerializedName("licensors")
    val licensors: List<LicensorsItem?>? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("producers")
    val producers: List<ProducersItem?>? = null,

    @SerializedName("background")
    val background: String? = null,

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("demographics")
    val demographics: List<DemographicsItem?>? = null
)

data class Broadcast(

    @SerializedName("string")
    val string: String? = null,

    @SerializedName("timezone")
    val timezone: String? = null,

    @SerializedName("time")
    val time: String? = null,

    @SerializedName("day")
    val day: String? = null
)

data class ThemesItem(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("mal_id")
    val malId: Int? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("url")
    val url: String? = null
)

data class Items(

    @SerializedName("per_page")
    val perPage: Int? = null,

    @SerializedName("total")
    val total: Int? = null,

    @SerializedName("count")
    val count: Int? = null
)

data class Jpg(

    @SerializedName("large_image_url")
    val largeImageUrl: String? = null,

    @SerializedName("small_image_url")
    val smallImageUrl: String? = null,

    @SerializedName("image_url")
    val imageUrl: String? = null
)

data class DemographicsItem(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("mal_id")
    val malId: Int? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("url")
    val url: String? = null
)

data class From(

    @SerializedName("month")
    val month: Int? = null,

    @SerializedName("year")
    val year: Int? = null,

    @SerializedName("day")
    val day: Int? = null
)

data class Webp(

    @SerializedName("large_image_url")
    val largeImageUrl: String? = null,

    @SerializedName("small_image_url")
    val smallImageUrl: String? = null,

    @SerializedName("image_url")
    val imageUrl: String? = null
)

data class Pagination(

    @SerializedName("has_next_page")
    val hasNextPage: Boolean? = null,

    @SerializedName("last_visible_page")
    val lastVisiblePage: Int? = null,

    @SerializedName("items")
    val items: Items? = null,

    @SerializedName("current_page")
    val currentPage: Int? = null
)

data class GenresItem(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("mal_id")
    val malId: Int? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("url")
    val url: String? = null
)

data class TitlesItem(

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("title")
    val title: String? = null
)

data class To(

    @SerializedName("month")
    val month: Any? = null,

    @SerializedName("year")
    val year: Any? = null,

    @SerializedName("day")
    val day: Any? = null
)

data class StudiosItem(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("mal_id")
    val malId: Int? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("url")
    val url: String? = null
)
