package com.example.lesson_12_lukin.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_12_lukin.data.model.BridgeListState
import com.example.lesson_12_lukin.data.repository.BridgesRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class BridgeDetailViewModel @Inject constructor(
    private val bridgesRepository: BridgesRepository,
) : ViewModel() {
    private val _bridgeLiveData = MutableLiveData<BridgeListState>()
    val bridgeLiveData: LiveData<BridgeListState> = _bridgeLiveData

    fun loadBridge(id: String) {
        viewModelScope.launch {
            _bridgeLiveData.postValue(BridgeListState.Loading)
            try {
                val bridges = bridgesRepository.getBridge(id)
                _bridgeLiveData.postValue(BridgeListState.Data(bridges))
            } catch (e: Exception) {
                _bridgeLiveData.postValue(BridgeListState.Error(e))
            }
        }
    }
}