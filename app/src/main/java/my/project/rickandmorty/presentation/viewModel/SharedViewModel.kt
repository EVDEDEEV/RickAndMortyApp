package my.project.rickandmorty.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.project.rickandmorty.data.models.CharacterListModel
import my.project.rickandmorty.data.repository.Repository
import retrofit2.Response

class SharedViewModel(val repository: Repository) : ViewModel() {

    var listCharacters = MutableLiveData<Response<CharacterListModel>>()
    var filterValue = MutableLiveData<Array<Int>>()
    var isFilter = MutableLiveData<Boolean>()

    init {
        filterValue.value = arrayOf(0, 0)
        isFilter.value = false

    }

    fun getCharacters(page: Int) {
        viewModelScope.launch {
            val characters = repository.getCharacters(page)
            listCharacters.value = characters
        }
    }

}