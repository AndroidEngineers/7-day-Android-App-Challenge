package com.android.engineer.mealmate.data.remote

import com.android.engineer.mealmate.data.remote.model.request.RegisterRequest
import com.android.engineer.mealmate.data.remote.model.response.IngredientsResponseItem
import com.android.engineer.mealmate.data.remote.model.response.NutrientsResponseItem
import com.android.engineer.mealmate.data.remote.model.response.RecipeInfoByIdResponse
import com.android.engineer.mealmate.data.remote.model.response.RegisterResponse
import com.android.engineer.mealmate.data.utils.CONTENT_HEADER_KEY
import com.android.engineer.mealmate.data.utils.CONTENT_HEADER_VALUE
import com.android.engineer.mealmate.data.utils.RECIPE_INFO_BY_ID
import com.android.engineer.mealmate.data.utils.REGISTER_API
import com.android.engineer.mealmate.data.utils.SEARCH_BY_INGREDIENTS
import com.android.engineer.mealmate.data.utils.SEARCH_BY_NUTRIENTS
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MealAPI {

    @GET(SEARCH_BY_NUTRIENTS)
    @Headers("$CONTENT_HEADER_KEY: $CONTENT_HEADER_VALUE")
    suspend fun searchByNutrients(@QueryMap filterByNutrients: HashMap<String, String>): List<NutrientsResponseItem>

    @GET(SEARCH_BY_INGREDIENTS)
    @Headers("$CONTENT_HEADER_KEY: $CONTENT_HEADER_VALUE")
    suspend fun searchByIngredients(@QueryMap filterByIngredients: HashMap<String, String>): List<IngredientsResponseItem>

    @GET(RECIPE_INFO_BY_ID)
    @Headers("$CONTENT_HEADER_KEY: $CONTENT_HEADER_VALUE")
    suspend fun getRecipeInformationById(@Path("id") recipeId: Int, @Query("apiKey") apiKey: String, @Query("includeNutrition") includeNutrition: Boolean = false): RecipeInfoByIdResponse

    @POST(REGISTER_API)
    @Headers("$CONTENT_HEADER_KEY: $CONTENT_HEADER_VALUE")
    suspend fun registerAccount(@Body registerRequest: RegisterRequest, @Query("apiKey") apiKey: String): RegisterResponse
}