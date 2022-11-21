package org.github.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
class User : PanacheEntity() {
    var name: String? = null
    var location: String? = null
    var blog: String? = null
    var company: String? = null
    lateinit var login: String
    lateinit var avatar: String
    lateinit var type: String
    lateinit var url: String

    var followers: Int? = null
    var following: Int? = null

    @Column(name = "created_at")
    lateinit var createdAt: LocalDate

    @Column(name = "updated_at")
    lateinit var updatedAt: LocalDate
}