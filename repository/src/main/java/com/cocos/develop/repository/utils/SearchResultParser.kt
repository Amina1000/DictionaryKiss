package com.cocos.develop.repository.utils

import com.cocos.develop.model.data.DataModel
import com.cocos.develop.model.data.Meanings
import com.cocos.develop.model.data.Translation
import com.cocos.develop.model.data.AppState
import com.cocos.develop.repository.room.HistoryEntity


fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                val historyEntity =
                    HistoryEntity(
                        searchResult[0].id!!, searchResult[0].text!!, "", "", "", "", "", ""
                    )
                if (searchResult[0].meanings.isNullOrEmpty()) {
                    historyEntity
                } else {
                    searchResult[0].meanings?.let {
                        historyEntity.translation = convertMeaningsToString(it)
                        it[0].let { firstEn ->
                            firstEn.imageUrl?.let { image ->
                                historyEntity.imageUrl = image
                            }
                            firstEn.previewUrl?.let { prev ->
                                historyEntity.previewUrl = prev
                            }
                            firstEn.transcription?.let { script ->
                                historyEntity.transcription = script
                            }
                            firstEn.translation?.let { transEntity ->
                                historyEntity.note = transEntity.note
                            }
                            firstEn.soundUrl?.let { sound ->
                                historyEntity.soundUrl = sound
                            }
                        }
                        historyEntity
                    }
                }
            }
        }
        else -> null
    }
}

fun convertMeaningsToString(meanings: List<Meanings>): String {
    val meaningsSeparatedByComma = StringBuilder()
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

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val searchResult = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(
                DataModel(
                    entity.id, entity.word,
                    listOf(
                        Meanings(
                            entity.id,
                            Translation(entity.translation, entity.note),
                            entity.imageUrl,
                            entity.previewUrl,
                            entity.transcription,
                            entity.soundUrl
                        )
                    )
                )
            )
        }
    }
    return searchResult
}
