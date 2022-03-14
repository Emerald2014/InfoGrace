package ru.kudesnik.infograce.model.entities.database

import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kudesnik.infograce.App

@androidx.room.Database(
    entities = [
        ItemEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        private const val DB_NAME = "item_database.db"

        val db: ItemDatabase by lazy {
            Room.databaseBuilder(
                App.appInstance,
                ItemDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}