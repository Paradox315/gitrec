package org.github.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.coroutines.awaitSuspending
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.github.dao.RecommendClient
import org.github.dao.RepoRepository
import org.github.dao.TopicRepository
import org.github.dao.UserRepository
import org.github.entity.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class RecommendService {
    @RestClient
    lateinit var recommendClient: RecommendClient


    @Inject
    lateinit var repoRepository: RepoRepository

    @Inject
    lateinit var topicRepository: TopicRepository

    @Inject
    lateinit var userRepository: UserRepository


    fun getPopular(n: Int, offset: Int): List<RepositoryVO>? {
        val items = recommendClient.getPopular(n, offset)
        return if (items.isNotEmpty()) {
            repoRepository.findByIds(items.map { it.id.toLong() }).map {
                RepositoryVO.fromRepository(it)
            }
        } else {
            null
        }
    }

    fun getPopularAsync(n: Int, offset: Int): Uni<List<Score>>? {
        val items = recommendClient.getPopularAsync(n, offset).onFailure().retry().atMost(3)
        return items
    }


    fun getPopularTopics(n: Int, offset: Int): List<Topic>? {
        val items = recommendClient.getPopularByCategory(n, offset, "topic")
        return if (items.isNotEmpty()) {
            topicRepository.findByIds(items.map { it.id.toLong() })
        } else null
    }

    fun getLatest(n: Int, offset: Int): List<RepositoryVO>? {
        val items = recommendClient.getLatest(n, offset)
        return if (items.isNotEmpty()) {
            repoRepository.findByIds(items.map { it.id.toLong() })
                .map { RepositoryVO.fromRepository(it) }
        } else null
    }

    fun getLatestTopics(n: Int, offset: Int): List<Topic>? {
        val items = recommendClient.getLatestByCategory(n, offset, "topic")
        return if (items.isNotEmpty()) {
            topicRepository.findByIds(items.map { it.id.toLong() })
        } else null
    }

    fun getItemNeighbors(n: Int, offset: Int, itemId: String): List<RepositoryVO>? {
        val items = recommendClient.getItemNeighbors(n, offset, itemId)
        return if (items.isNotEmpty()) {
            repoRepository.findByIds(items.map { it.id.toLong() })
                .map { RepositoryVO.fromRepository(it) }
        } else null
    }

    fun getUserNeighbors(n: Int, offset: Int, userId: String): List<User>? {
        val items = recommendClient.getUserNeighbors(n, offset, userId)
        return if (items.isNotEmpty()) {
            userRepository.findByIds(items.map { it.id.toLong() })
        } else null
    }
}