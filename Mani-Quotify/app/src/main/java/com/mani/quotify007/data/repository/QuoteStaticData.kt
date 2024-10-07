package com.mani.quotify007.data.repository

import com.mani.quotify007.domain.model.Quote

val quotesDataList = mutableListOf(
    Quote(
        _id = "111",
        content = "The greatest glory in living lies not in never falling, " +
                "but in rising every time we fall.",
        author = "Nelson Mandela"
    ),
    Quote(
        _id = "222",
        content = "The way to get started is to quit talking and begin doing.",
        author = "Walt Disney"
    ),
    Quote(
        _id = "333",
        content = "Your time is limited, so don't waste it living someone else's life.",
        author = "Steve Jobs"
    ),
    Quote(
        _id = "444",
        content = "If life were predictable it would cease to be life, and be without flavor.",
        author = "Eleanor Roosevelt"
    ),
    Quote(
        _id = "555",
        content = "If you look at what you have in life, you'll always have more. " +
                "If you look at what you don't have in life, you'll never have enough.",
        author = "Oprah Winfrey"
    ),
    Quote(
        _id = "666",
        content = "If you set your goals ridiculously high and it's a failure, " +
                "you will fail above everyone else's success.",
        author = "James Cameron"
    )
)