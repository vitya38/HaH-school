package com.example.lesson_12_lukin.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_12_lukin.data.model.BridgesListState
import com.example.lesson_12_lukin.data.repository.BridgesRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class BridgeListViewModel @Inject constructor(
    private val bridgesRepository: BridgesRepository,
) : ViewModel() {
    private val _bridgesLiveData = MutableLiveData<BridgesListState>()
    val bridgesLiveData: LiveData<BridgesListState> = _bridgesLiveData

    fun loadBridges() {
        viewModelScope.launch {
            _bridgesLiveData.postValue(BridgesListState.Loading)
            try {
                val bridges = bridgesRepository.getBridges()
                _bridgesLiveData.postValue(BridgesListState.Data(bridges))
            } catch (e: Exception) {
                _bridgesLiveData.postValue(BridgesListState.Error(e))
            }
        }
    }
}