package ru.kudesnik.infograce.repository

import android.content.Context
import ru.kudesnik.infograce.R
import ru.kudesnik.infograce.model.Item
import ru.kudesnik.infograce.usecases.UseCaseGetSharedPref

class RepositoryImpl : Repository {
    private val myUseCase: UseCaseGetSharedPref = UseCaseGetSharedPref()
    override fun getItems(context: Context): List<Item> {


        return listOf(
            Item(
                0,
                "Слой делян",
                myUseCase.getCurrentPosition(0, context),
                myUseCase.getCurrentSwitch(0, context),
                myUseCase.getCurrentSlider(0, context),
                R.drawable.ic_item_0,
                isVisible = true
            ),
            Item(
                1,
                "Сигналы о лесоизменениях, тестовая выборка с ув-ным шагом",
                myUseCase.getCurrentPosition(1, context),

                myUseCase.getCurrentSwitch(1, context),
                myUseCase.getCurrentSlider(1, context),
                R.drawable.ic_item_1,
                isVisible = true
            ),
            Item(
                2,
                "Преграды для прохождения огня",
                myUseCase.getCurrentPosition(2, context),

                myUseCase.getCurrentSwitch(2, context),
                myUseCase.getCurrentSlider(2, context),
                R.drawable.ic_item_2,
                isVisible = true
            ),
            Item(
                3,
                "Маска облачности от 02.07.2021",
                myUseCase.getCurrentPosition(3, context),

                myUseCase.getCurrentSwitch(3, context),
                myUseCase.getCurrentSlider(3, context),
                R.drawable.ic_item_3,
                isVisible = true

            ),
            Item(
                4,
                "Маска облачности от 02.07.2021",
                4,
                myUseCase.getCurrentSwitch(4, context),
                myUseCase.getCurrentSlider(4, context),
                R.drawable.ic_item_3,
                isVisible = true
            ),
            Item(
                5,
                "Папка со слоями",
                5,
                myUseCase.getCurrentSwitch(5, context),
                myUseCase.getCurrentSlider(5, context),
                R.drawable.ic_item_5,
                isVisible = true
            ),
            Item(
                6,
                "Сигналы о лесоизменениях, тестовая выборка с ув-ным шагом",
                6,
                myUseCase.getCurrentSwitch(6, context),
                myUseCase.getCurrentSlider(6, context),
                R.drawable.ic_item_1,
                isVisible = true
            ),
            Item(
                7,
                "Преграды для прохождения огня",
                7,
                myUseCase.getCurrentSwitch(7, context),
                myUseCase.getCurrentSlider(7, context),
                R.drawable.ic_item_2,
                isVisible = true
            ),
            Item(
                8,
                "Контуры гарей",
                8,
                myUseCase.getCurrentSwitch(8, context),
                myUseCase.getCurrentSlider(8, context),
                R.drawable.ic_item_3,
                isVisible = false
            ),
            Item(
                9,
                "Маска облачности от 02.07.2021",
                9,
                myUseCase.getCurrentSwitch(9, context),
                myUseCase.getCurrentSlider(9, context),
                R.drawable.ic_item_3,
                isVisible = true
            ),
            Item(
                10,
                "Маска облачности от 02.07.2021",
                10,
                myUseCase.getCurrentSwitch(10, context),
                myUseCase.getCurrentSlider(10, context),
                R.drawable.ic_item_3,
                isVisible = true
            ),
            Item(
                11,
                "Маска облачности от 02.07.2021",
                11,
                myUseCase.getCurrentSwitch(11, context),
                myUseCase.getCurrentSlider(11, context),
                R.drawable.ic_item_11,
                isVisible = true
            ),
            Item(
                12,
                "Маска облачности от 02.07.2021",
                12,
                myUseCase.getCurrentSwitch(12, context),
                myUseCase.getCurrentSlider(12, context),
                R.drawable.ic_item_3,
                isVisible = true
            ),
        )
    }

//    private fun getCurrentSlider(pos: Int): Int {
//        return myUseCase.getCurrentSlider(pos, co)
//    }
//
//    private fun getCurrentSwitch(pos: Int): Boolean {
//        return useCase.getCurrentSwitch(pos, context)
//    }
}