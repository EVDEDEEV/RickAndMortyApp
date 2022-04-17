package my.project.rickandmorty.data.api

import my.project.rickandmorty.data.models.CharacterListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterListModel>
}