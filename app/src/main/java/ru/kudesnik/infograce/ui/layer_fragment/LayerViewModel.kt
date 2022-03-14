package ru.kudesnik.infograce.ui.layer_fragment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kudesnik.infograce.model.AppState
import ru.kudesnik.infograce.model.entities.Item
import ru.kudesnik.infograce.repository.Repository

class LayerViewModel(private val repository: Repository) : ViewModel() {
    val liveData = MutableLiveData<AppState>()
    val itemLiveData: LiveData<AppState>
        get() {
            return liveData
        }
    val itemLiveDataUpdate = MutableLiveData<Unit>()

    fun getLiveData(): LiveData<AppState> = liveData

    fun getAllItems() {
        liveData.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(AppState.Success(repository.getAllItems()))
        }
    }

    fun update(item: Item) {
        liveData.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            itemLiveDataUpdate.postValue(repository.updateItem(item))
        }
    }

    fun getMainSliderPosition(): Int {
        var isCheckedInt = 0
        var test = 0
        var allItems = listOf<Item>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                allItems = repository.getAllItems()
                withContext(Dispatchers.Main) {
                    for (item in allItems) {
                        if (item.isCheckedSwitch) isCheckedInt++
                    }
                    test = when (isCheckedInt) {
                        allItems.size -> 2
                        0 -> 0
                        else -> 1
                    }
                }
            }

        }
        return test
    }


    fun getItems(context: Context) {
//        liveData.value = AppState.Loading
        liveData.postValue(AppState.Success(repository.getAllItems()))
//        viewModelScope.launch(Dispatchers.Main) {
//            liveData.postValue(AppState.Success(repository.getItems(context)))
//        }
    }
}