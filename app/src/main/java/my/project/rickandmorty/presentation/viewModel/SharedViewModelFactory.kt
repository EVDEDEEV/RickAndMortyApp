package my.project.rickandmorty.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.project.rickandmorty.data.repository.Repository
import my.project.rickandmorty.presentation.fragments.list.SharedViewModel

class SharedViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(repository) as T
    }
}