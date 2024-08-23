package com.example.taskninja20.model

data class Task(val taskTitle: String="",
                val taskDescription: String ="",
                val taskStatus: Int = 0,
                val priority: Int = 0,
                val taskCategory: String, )