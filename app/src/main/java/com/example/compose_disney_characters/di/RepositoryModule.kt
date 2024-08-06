package com.example.compose_disney_characters.di

import com.example.compose_disney_characters.repository.repositoryImpl.DisneyCharactersListRetrofitRepository
import com.example.compose_disney_characters.repository.DisneyCharactersListRepository
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
    abstract fun bindDisneyListRepository(
        charactersListRepository: DisneyCharactersListRetrofitRepository
    ): DisneyCharactersListRepository
}