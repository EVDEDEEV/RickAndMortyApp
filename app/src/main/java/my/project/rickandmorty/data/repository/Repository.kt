package my.project.rickandmorty.data.repository

import my.project.rickandmorty.data.api.Api
import my.project.rickandmorty.data.models.CharacterListModel
import retrofit2.Response

class Repository {
    suspend fun getCharacters(page: Int): Response<CharacterListModel> {
        return Api.api.getCharacters(page)
    }
}