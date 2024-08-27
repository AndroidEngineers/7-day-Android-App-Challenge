package com.abhijith.animex.data.network.model

import com.google.gson.annotations.SerializedName

data class AnimeDto(

    @SerializedName("title_japanese")
    val titleJapanese: String? = null,

    @SerializedName("favorites")
    val favorites: Int? = null,

    @SerializedName("broadcast")
    val broadcast: BroadcastDto? = null,

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
    val trailer: TrailerDto? = null,

    @SerializedName("duration")
    val duration: String? = null,

    @SerializedName("score")
    val score: Double? = null,

    @SerializedName("themes")
    val themes: List<ThemesDto?>? = null,

    @SerializedName("approved")
    val approved: Boolean? = null,

    @SerializedName("genres")
    val genres: List<GenresDto?>? = null,

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
    val aired: AiredDto? = null,

    @SerializedName("images")
    val images: AnimeImagesDto? = null,

    @SerializedName("studios")
    val studios: List<StudiosDto?>? = null,

    @SerializedName("mal_id")
    val malId: Int? = null,

    @SerializedName("titles")
    val titles: List<TitlesDto?>? = null,

    @SerializedName("synopsis")
    val synopsis: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("producers")
    val producers: List<ProducersDto?>? = null,

    @SerializedName("background")
    val background: String? = null,

    @SerializedName("status")
    val status: String? = null,
)