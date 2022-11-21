package org.github.dao

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.github.entity.Repository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class RepoRepository : PanacheRepository<Repository> {
    fun findByIds(ids: List<Long>) = find("id in ?1", ids).list()

}