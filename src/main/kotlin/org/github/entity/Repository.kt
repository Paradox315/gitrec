package org.github.entity

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.time.LocalDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "repository")
class Repository : PanacheEntity() {
    lateinit var name: String
    lateinit var owner: String

    @Column(name = "full_name")
    var fullName: String? = null
    var description: String? = null
    var url: String? = null
    var homepage: String? = null
    lateinit var language: String
    var forks: Int? = null
    var stars: Int? = null
    var topics: String? = null

    @Column(name = "created_at")
    lateinit var createdAt: LocalDate

    @Column(name = "updated_at")
    lateinit var updatedAt: LocalDate
}