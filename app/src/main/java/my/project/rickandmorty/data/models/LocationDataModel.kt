package my.project.rickandmorty.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDataModel(
    var name: String,
) : Parcelable


