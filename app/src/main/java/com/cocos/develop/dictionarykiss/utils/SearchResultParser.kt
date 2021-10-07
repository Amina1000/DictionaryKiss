package com.cocos.develop.dictionarykiss.utils

import com.cocos.develop.model.data.DataModel
import com.cocos.develop.model.data.Meanings
import com.cocos.develop.model.data.AppState


fun parseSearchResults(data: AppState): AppState {
    val newSearchResults = arrayListOf<DataModel>()
    when (data) {
        is AppState.Success -> {
            val searchResults = data.data
            if (!searchResults.isNullOrEmpty()) {
                for (searchResult in searchResults) {
                    parseResult(searchResult, newSearchResults)
                }
            }
        }
    }

    return AppState.Success(newSearchResults)
}

private fun parseResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings!!) {
            if (meaning.translation != null && !meaning.translation!!.translation.isNullOrBlank()) {
                newMeanings.add(
                    Meanings(
                        meaning.id, meaning.translation, meaning.imageUrl,
                        meaning.previewUrl, meaning.transcription, meaning.soundUrl
                    )
                )
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.id, dataModel.text, newMeanings))
        }
    }
}

fun convertMeaningsToString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComma = StringBuilder()
    meanings.forEach { meaning ->
        meaning.translation?.let {
            meaningsSeparatedByComma.append(it.translation).append("\n")
            it.note?.let { note ->
                meaningsSeparatedByComma.append(note)
            }
        }
    }
    return meaningsSeparatedByComma.toString()
}
