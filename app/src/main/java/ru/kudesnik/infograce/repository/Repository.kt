package ru.kudesnik.infograce.repository

import android.content.Context
import ru.kudesnik.infograce.Item
import ru.kudesnik.infograce.usecases.UseCaseGetSharedPref

interface Repository {
    fun getItems(context: Context):List<Item>
}