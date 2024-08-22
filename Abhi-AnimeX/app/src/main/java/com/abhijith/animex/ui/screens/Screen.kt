package com.abhijith.animex.ui.screens

sealed class Screen(val route: String) {
    data object AnimeList : Screen("animeList")
    data object AnimeDetails : Screen("animeDetails/{selectedAnimeItem}")
}