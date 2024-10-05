package com.example.studybuddy.domain.model

data class BottomNavigationItem(
    val title:String,
    val selectedIcon: Int,
    val unselectedIcon:Int,
    val hasNews:Boolean,
    val badgeCount:Int? =null
)
