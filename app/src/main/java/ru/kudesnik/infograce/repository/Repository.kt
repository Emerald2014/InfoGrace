package ru.kudesnik.infograce.repository

import android.content.Context
import ru.kudesnik.infograce.model.Item

interface Repository {
    fun getItems(context: Context):List<Item>
}