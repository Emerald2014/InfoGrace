package ru.kudesnik.infograce.repository

import android.content.Context
import ru.kudesnik.infograce.model.entities.Item

interface Repository {
    fun getItems(context: Context):List<Item>

    fun getAllItems(): List<Item>
    fun updateItem(item:Item)
    fun insertItem(item: Item)
    fun getItemByName(name: String): List<Item>

}