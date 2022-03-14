package ru.kudesnik.infograce.model.entities.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey(autoGenerate = false) val idEntity: Int,
    val nameEntity: String,
    var sliderEntity: Int,
    var switchEntity: Boolean ,
    var positionEntity: Int,
    var visibleEntity: Boolean ,
    val imageEntity: Int
)