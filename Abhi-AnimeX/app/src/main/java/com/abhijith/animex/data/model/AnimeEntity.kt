package com.abhijith.animex.data.model

// temporary implementation to pass data from viewmodel instead of hardcoding it in composable
// to remove the default values once data is added
data class AnimeEntity(
    val title: String = listOf(
        "\"\\\"Oshi no Ko\\\" 2nd Season\"",
        "Kami no Tou: Ouji no Kikan",
        "Shikanoko Nokonoko Koshitantan"
    ).random(),
    val source: String = listOf(
        "Manga",
        "Light Novel",
        "Novel",
        "Game",
        "Web Manga",
        "Other"
    ).random(),
    val year: String = listOf("2024", "2017", "2019", "2021", "").random(),
    val japaneseName: String = listOf(
        "【推しの子】第2期",
        "神之塔 -Tower of God- 王子の帰還",
        "しかのこのこのここしたんたん"
    ).random(),
    val rating: String = listOf("", "4.7", "3.8", "4.9").random(),
    val rank: String = listOf("123", "37", "381", "451", "").random(),
    val score: String = listOf("8.2", "7.8", "", "9.5", "6.7").random(),
    val synopsis: String = "This Japanese Lorem Ipsum is based on the kanji frequency count at tidraso.co.uk and includes about 50% kanji, 25% hiragana, 20% katakana and 5% roman numerals and punctuation. Katakana and hiragana cluster in strings between 1 to 4 chars at random points in each paragraph. Hiragana occurs more often at the end of sentences, rather in clumps of 1 to 4 chars rather than just single chars. Katakana is very unlikely to appear as a single character in Japanese text, but hiragana could. Exclamation and question marks are \"double-byte\", not standard ascii ones. Suggestions for improvements or alternatives are welcome.",
    val genres: List<String> = listOf(
        "comedy",
        "gag humor",
        "school",
        "adventure",
        "fantasy"
    ).shuffled().take(4),
    val imageUrl: String = listOf(
        "https://cdn.myanimelist.net/images/anime/1107/143536l.webp",
        "https://cdn.myanimelist.net/images/anime/1084/144617l.webp",
        "https://cdn.myanimelist.net/images/anime/1332/143513l.webp",
        "https://cdn.myanimelist.net/images/anime/1607/143547l.webp"
    ).random(),
    val youtubeId: String = listOf(
        "QMuajQlx64c",
        "Bf4XTzeUBHo",
        "441v-JXm0CE",
        "SjYlGEvG2Go",
        "uytJ6_KTCZI"
    ).random()
)
