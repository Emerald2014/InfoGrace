package ru.kudesnik.infograce

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import ru.kudesnik.infograce.repository.Repository

class LayerViewModel(private val repository: Repository): ViewModel() {
    private val liveData = MutableLiveData<AppState>()
    fun getLiveData(): LiveData<AppState> = liveData

    fun getItems(context:Context) {
//        liveData.value = AppState.Loading
        liveData.postValue(AppState.Success(repository.getItems(context)))
//        viewModelScope.launch(Dispatchers.Main) {
//            liveData.postValue(AppState.Success(repository.getItems(context)))
//        }
    }
}