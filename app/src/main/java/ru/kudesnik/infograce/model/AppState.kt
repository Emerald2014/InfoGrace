package ru.kudesnik.infograce.model

sealed class AppState {
    data class Success(val modelData: List<Item>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}