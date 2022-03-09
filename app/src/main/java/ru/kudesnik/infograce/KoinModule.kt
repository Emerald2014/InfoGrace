package ru.kudesnik.infograce

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.kudesnik.infograce.LayerViewModel
import ru.kudesnik.infograce.repository.Repository
import ru.kudesnik.infograce.repository.RepositoryImpl

val appModule = module {

    single<Repository> { RepositoryImpl() }
    viewModel { LayerViewModel(get()) }

}