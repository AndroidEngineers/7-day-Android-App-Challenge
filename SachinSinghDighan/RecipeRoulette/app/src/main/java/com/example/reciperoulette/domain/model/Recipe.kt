package com.example.reciperoulette.domain.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("vegetarian")
    val vegetarian: Boolean = false,
    @SerializedName("vegan")
    val vegan: Boolean = false,
    @SerializedName("glutenFree")
    val glutenFree: Boolean = true,
    @SerializedName("dairyFree")
    val dairyFree: Boolean = true,
    @SerializedName("veryHealthy")
    val veryHealthy: Boolean = true,
    @SerializedName("cheap")
    val cheap: Boolean = false,
    @SerializedName("veryPopular")
    val veryPopular: Boolean = false,
    @SerializedName("sustainable")
    val sustainable: Boolean = false,
    @SerializedName("lowFodmap")
    val lowFodmap: Boolean = false,
    @SerializedName("weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int = 0,
    @SerializedName("gaps")
    val gaps: String = "",
    @SerializedName("preparationMinutes")
    val preparationMinutes: String? = null,
    @SerializedName("cookingMinutes")
    val cookingMinutes: String? = null,
    @SerializedName("aggregateLikes")
    val aggregateLikes: Int = 0,
    @SerializedName("healthScore")
    val healthScore: Int = 0,
    @SerializedName("creditsText")
    val creditsText: String = "",
    @SerializedName("license")
    val license: String = "",
    @SerializedName("sourceName")
    val sourceName: String = "",
    @SerializedName("pricePerServing")
    val pricePerServing: Double = 0.0,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredients> = listOf(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int = 0,
    @SerializedName("servings")
    val servings: Int = 0,
    @SerializedName("sourceUrl")
    val sourceUrl: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("imageType")
    val imageType: String = "",
    @SerializedName("summary")
    val summary: String = "",
    @SerializedName("instructions")
    val instructions: String = "",
    @SerializedName("cuisines")
    val cuisines: List<String> = listOf(),
    @SerializedName("dishTypes")
    val dishTypes: List<String> = listOf(),
    @SerializedName("diets")
    val diets: List<String> = listOf(),
    @SerializedName("occasions")
    val occasions: List<String> = listOf(),
    @SerializedName("analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstruction> = listOf(),
    @SerializedName("originalId")
    val originalId: Int? = null,
    @SerializedName("spoonacularScore")
    val spoonacularScore: Double = 0.0,
    @SerializedName("spoonacularSourceUrl")
    val spoonacularSourceUrl: String = "",
    )
