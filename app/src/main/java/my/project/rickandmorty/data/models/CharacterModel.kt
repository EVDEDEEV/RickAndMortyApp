package my.project.rickandmorty.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var gender: String,
    var origin: LocationDataModel,
    var location: LocationDataModel,
    var image: String,
    var episode: List<String>,
) : Parcelable
