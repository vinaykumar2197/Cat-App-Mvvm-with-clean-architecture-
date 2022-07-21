package com.vinay.catapp.domain.repository

import androidx.paging.PagingData
import com.vinay.catapp.domain.model.CatBreedModel
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getCatBreedList(
        fetchFromRemote: Boolean
    ): Flow<PagingData<CatBreedModel>>
}