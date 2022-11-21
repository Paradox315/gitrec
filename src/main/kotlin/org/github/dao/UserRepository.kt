package org.github.dao

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.github.entity.User
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRepository : PanacheRepository<User> {
    fun findByIds(ids: List<Long>) = find("id in ?1", ids).list()
}