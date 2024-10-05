package com.example.jay_recipesx.features.RecipeHome.Data.Repositories

import com.example.jay_recipesx.features.RecipeHome.Data.Models.RecipeModel
import com.example.jay_recipesx.features.RecipeHome.Domain.Repositories.IRecipeRepo
import kotlin.random.Random

class RecipeRepoImpl : IRecipeRepo {
    val recipes = mutableListOf(
        RecipeModel(
            id = 1,
            name = "Spaghetti Bolognese",
            ingredients = listOf("Spaghetti", "Ground beef", "Tomato sauce", "Onion", "Garlic", "Olive oil"),
            instructions = "Cook the spaghetti. Brown the beef in a pan with onions and garlic. Add tomato sauce and simmer. Serve over spaghetti.",
            cookingTime = 30,
            servings = 4,
            isVegetarian = false,
            imageUrl = "https://asset20.ckassets.com/blog/wp-content/uploads/sites/5/2019/12/Pav-Bhaji.jpg"
        ),
        RecipeModel(
            id = 2,
            name = "Vegetable Stir-fry",
            ingredients = listOf("Broccoli", "Carrots", "Bell peppers", "Soy sauce", "Garlic", "Ginger"),
            instructions = "Stir-fry vegetables in a hot pan with garlic and ginger. Add soy sauce and serve with rice.",
            cookingTime = 20,
            servings = 2,
            isVegetarian = true,
            imageUrl = "https://asset20.ckassets.com/blog/wp-content/uploads/sites/5/2019/12/Pav-Bhaji.jpg"
        ),
        RecipeModel(
            id = 3,
            name = "Chicken Curry",
            ingredients = listOf("Chicken", "Curry powder", "Coconut milk", "Onion", "Garlic", "Ginger"),
            instructions = "Sauté onions, garlic, and ginger. Add chicken and curry powder, then stir in coconut milk. Simmer until cooked.",
            cookingTime = 40,
            servings = 4,
            isVegetarian = false,
            imageUrl = "https://asset20.ckassets.com/blog/wp-content/uploads/sites/5/2019/12/Pav-Bhaji.jpg"
        ),
        RecipeModel(
            id = 4,
            name = "Pancakes",
            ingredients = listOf("Flour", "Milk", "Eggs", "Baking powder", "Sugar", "Butter"),
            instructions = "Mix all ingredients into a smooth batter. Cook on a hot griddle until golden brown.",
            cookingTime = 15,
            servings = 3,
            isVegetarian = true,
            imageUrl = "https://asset20.ckassets.com/blog/wp-content/uploads/sites/5/2019/12/Pav-Bhaji.jpg"
        )
    )

    override fun getRandomRecipe(): RecipeModel {
        val randomIndex = Random.nextInt(recipes.size)
        return recipes[randomIndex];
    }

    override fun getSimilarRecipes(id: String) : List<RecipeModel> {
        return recipes;
    }
}