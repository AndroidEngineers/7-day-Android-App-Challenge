package com.example.jay_recipesx.Core.AppFunctions

class AppFunctions {
    companion object {
        fun removeHtmlTags(input: String): String {
            // Regex to match HTML-like tags
            val regex = "<[^>]*>".toRegex()
            // Replace the matched tags with an empty string
            return input.replace(regex, "")
        }

        fun addBulletsToInstructions(instructions: String): String {
            // Split the instructions by line breaks
            val lines = instructions.split("\n")

            // Add a bullet to each line and include an extra line break
            return lines.joinToString(separator = "\n\n") { "• $it" } // Two newlines for extra space
        }
    }
}