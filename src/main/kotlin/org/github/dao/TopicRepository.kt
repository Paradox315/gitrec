package org.github.dao

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.github.entity.Topic
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TopicRepository : PanacheRepository<Topic> {
    fun findByIds(ids: List<Long>) = find("id in ?1", ids).list()

}