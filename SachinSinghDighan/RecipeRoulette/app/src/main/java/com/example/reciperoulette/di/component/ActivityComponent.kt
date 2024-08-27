package com.example.reciperoulette.di.component

import com.example.reciperoulette.di.ActivityScope
import com.example.reciperoulette.di.module.ActivityModule
import com.example.reciperoulette.presentation.ui.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [RecipeRouletteApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: MainActivity)

}