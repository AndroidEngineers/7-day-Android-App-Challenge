package com.example.anifetch.utils

import com.example.anifetch.models.AnimeItem
import com.example.anifetch.models.AnimeResponse
import com.example.anifetch.models.Character
import com.example.anifetch.models.CharacterResponse
import com.example.anifetch.models.ImageUrl
import com.example.anifetch.models.Images

object MockData {

    fun getMockAnimeResponse(): AnimeResponse{
        return AnimeResponse(
            data = listOf(
                AnimeItem(
                    mal_id = 1,
                    title = "Naruto",
                    synopsis = "Naruto Uzumaki is a young ninja with a sealed demon fox spirit within him.",
                    images = Images(
                        jpg = ImageUrl(image_url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtGdWPi7Q5WwfCSKyP8JZIYt0_L_J-ijxi_Q&s")
                    )
                ),
                AnimeItem(
                    mal_id = 2,
                    title = "One Piece",
                    synopsis = "Monkey D. Luffy and his pirate crew seek the legendary treasure, One Piece.",
                    images = Images(
                        jpg = ImageUrl(image_url = "https://t4.ftcdn.net/jpg/04/66/96/07/360_F_466960799_UjSYk2XvePE9HEt1R1LO54xOHoJRfSx7.jpg")
                    )
                )
            )
        )
    }

    fun getMockCharacterResponse(): CharacterResponse{
        return CharacterResponse(
            data = listOf(
                Character(
                    mal_id = 1,
                    name = "Naruto Uzumaki",
                    role = "Protagonist",
                    images = Images(
                        jpg = ImageUrl(image_url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtGdWPi7Q5WwfCSKyP8JZIYt0_L_J-ijxi_Q&s")
                    )
                ),
                Character(
                    mal_id = 2,
                    name = "Sasuke Uchiha",
                    role = "Rival",
                    images = Images(
                        jpg = ImageUrl(image_url = "https://upload.wikimedia.org/wikipedia/en/4/42/SasukeKishimoto.jpg")
                    )
                )
            )
        )
    }
}