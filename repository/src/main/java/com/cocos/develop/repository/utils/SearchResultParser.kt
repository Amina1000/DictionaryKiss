package com.cocos.develop.repository.utils

import com.cocos.develop.model.data.DataModel
import com.cocos.develop.model.data.Meanings
import com.cocos.develop.model.data.Translation
import com.cocos.develop.repository.room.HistoryEntity


fun convertDataModelSuccessToEntity(dataModel: DataModel): HistoryEntity {

    val historyEntity =
        HistoryEntity(
            dataModel.id!!, dataModel.text!!, "", "", "", "",
            "", "", dataModel.favorite
        )
    dataModel.meanings?.let {
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
    }
    return historyEntity
}


fun convertMeaningsToString(meanings: List<Meanings>): String {
    val meaningsSeparatedByComma = StringBuilder()

    meanings.forEach { meaning ->
        meaning.translation?.let {
            meaningsSeparatedByComma.append(it.translation).append("\n")
            it.note?.let { note ->
                if (note!="") {
                    meaningsSeparatedByComma.append("(").append(note).append(")").append("\n")
                }
            }
        }
    }

    meaningsSeparatedByComma.setLength(meaningsSeparatedByComma.length - 1)
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
                    ),entity.favorite
                )
            )
        }
    }
    return searchResult
}
