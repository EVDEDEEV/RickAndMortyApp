package my.project.rickandmorty.presentation.fragments.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.project.rickandmorty.data.models.CharacterListModel
import my.project.rickandmorty.data.repository.Repository
import retrofit2.Response

class SharedViewModel(private val repository: Repository) : ViewModel() {

    val listCharacters = MutableLiveData<Response<CharacterListModel>>()

    fun getCharacters(page: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val characters = repository.getCharacters(page)
            listCharacters.postValue(characters)
        }
    }
}

