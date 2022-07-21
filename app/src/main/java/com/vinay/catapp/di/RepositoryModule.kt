package com.vinay.catapp.di

import com.vinay.catapp.data.repository.AppRepositoryImpl
import com.vinay.catapp.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: AppRepositoryImpl
    ): AppRepository
}