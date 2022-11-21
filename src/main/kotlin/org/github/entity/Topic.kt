package org.github.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "topic")
class Topic : PanacheEntity() {
    lateinit var name: String

    @Column(name = "display_name")
    var displayName: String? = null
    var description: String? = null
    var released: String? = null

    @Column(name = "created_at")
    lateinit var createdAt: LocalDate

    @Column(name = "updated_at")
    lateinit var updatedAt: LocalDate
}