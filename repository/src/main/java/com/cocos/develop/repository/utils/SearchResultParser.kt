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
                    HistoryEntity(searchResult[0].id!!, searchResult[0].text!!, "", ""
                ,"","","","")
                if (searchResult[0].meanings.isNullOrEmpty()) {
                    historyEntity
                } else {
                    searchResult[0].meanings?.get(0)?.let {
                        it.imageUrl?.let { image->
                            historyEntity.imageUrl = image
                        }
                        it.previewUrl?.let { prev->
                            historyEntity.previewUrl =prev
                        }
                        it.transcription?.let { script->
                            historyEntity.transcription = script
                        }
                        it.translation?.let { transEntity->
                            historyEntity.translation = transEntity.translation
                            historyEntity.note = transEntity.note
                        }
                        it.soundUrl?.let{sound ->
                            historyEntity.soundUrl = sound
                        }
                    historyEntity
                    }
                }
            }
        }
        else -> null
    }
}

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val searchResult = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(
                DataModel(
                    entity.id, entity.word,
                    listOf(Meanings(entity.id, Translation(entity.translation, entity.note),
                        entity.imageUrl,entity.previewUrl, entity.transcription, entity.soundUrl))
                )
            )
        }
    }
    return searchResult
}
