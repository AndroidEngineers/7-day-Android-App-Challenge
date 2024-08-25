package com.example.reciperoulette.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ActivityContext


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabaseName
