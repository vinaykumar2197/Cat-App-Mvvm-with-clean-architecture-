package com.vinay.catapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vinay.catapp.data.remote.AppApi
import com.vinay.catapp.domain.model.CatBreedModel
import com.vinay.catapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val api: AppApi,
): AppRepository {

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    override suspend fun getCatBreedList(fetchFromRemote: Boolean): Flow<PagingData<CatBreedModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { CatBreedPagingSource(api) }
        ).flow
    }
}