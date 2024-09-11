package com.example.cleanarchitecture.domain.mappers

import com.example.cleanarchitecture.data.dto.DevelopersDTO
import com.example.cleanarchitecture.data.local.DeveloperTable
import com.example.cleanarchitecture.domain.model.Developers

object DevelopersMapper {
    fun mapDevelopersTableToDevelopers(input: List<DeveloperTable>): List<Developers> {
        return input.map { developerTable ->
            Developers(
                isFavourite = developerTable.isFavourite,
                username = developerTable.username,
                avatar = developerTable.avatar,
                url = developerTable.url
            )
        }
    }
}