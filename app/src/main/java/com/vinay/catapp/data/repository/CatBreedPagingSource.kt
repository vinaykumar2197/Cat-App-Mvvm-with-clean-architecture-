package com.vinay.catapp.data.repository

/**
 * Created by vinaymishra on 20,July,2022
 */
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vinay.catapp.data.remote.AppApi
import com.vinay.catapp.domain.model.CatBreedModel
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 0


class CatBreedPagingSource(
    private val api: AppApi
) : PagingSource<Int, CatBreedModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatBreedModel> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.getListings(10,position)
            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position,
                nextKey = if (response.isEmpty()) null else position + 1

            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatBreedModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}