package ru.kudesnik.infograce

data class Item(
    val id: Int,
    val name: String,
    var position: Int,
    var isCheckedSwitch:Boolean,
    var sliderValue: Int
) {

}
