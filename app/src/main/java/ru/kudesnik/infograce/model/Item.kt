package ru.kudesnik.infograce.model

data class Item(
    val id: Int,
    val name: String,
    var position: Int,
    var isCheckedSwitch:Boolean,
    var sliderValue: Int,
    val image: Int,
    var isVisible: Boolean
) {

}
