package ru.kudesnik.infograce.model.entities.database

import androidx.room.*


@Dao
interface ItemDao {
    @Update
    fun update(entity: ItemEntity)

    @Delete
    fun delete(entity: ItemEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: ItemEntity)

    @Query("SELECT * FROM ItemEntity")
    fun all(): List<ItemEntity>

    @Query("SELECT * FROM ItemEntity WHERE nameEntity LIKE :item")
    fun getDataByWord(item: String):List<ItemEntity>
}