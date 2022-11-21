package org.github.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.time.LocalDate

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class RepositoryVO(
    var id: Long? = null,
    var name: String? = null,
    var owner: String? = null,
    var fullName: String? = null,
    var description: String? = null,
    var url: String? = null,
    var homepage: String? = null,
    var language: String? = null,
    var forks: Int? = null,
    var stars: Int? = null,
    var topics: List<String>? = null,
    var createdAt: LocalDate,
    var updatedAt: LocalDate
) {
    companion object {
        fun fromRepository(repository: Repository): RepositoryVO {
            return RepositoryVO(
                id = repository.id,
                name = repository.name,
                owner = repository.owner,
                fullName = repository.fullName,
                description = repository.description,
                url = repository.url,
                homepage = repository.homepage,
                language = repository.language,
                forks = repository.forks,
                stars = repository.stars,
                topics = repository.topics?.split(",") ?: emptyList(),
                createdAt = repository.createdAt,
                updatedAt = repository.updatedAt
            )
        }
    }
}
