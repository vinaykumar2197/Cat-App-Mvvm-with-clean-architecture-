package com.vinay.catapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vinay.catapp.domain.model.CatBreedModel
import com.vinay.catapp.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PLViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {
    suspend fun getCatBreedList() : Flow<PagingData<CatBreedModel>> {
          return repository.getCatBreedList(true)
                .cachedIn(viewModelScope)
    }
}