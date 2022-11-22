package org.github.service

import io.smallrye.mutiny.Uni
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.github.dao.RecommendClient
import org.github.dao.RepoRepository
import org.github.dao.TopicRepository
import org.github.dao.UserRepository
import org.github.entity.RepositoryDto
import org.github.entity.Score
import org.github.entity.Topic
import org.github.entity.User
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class RecommendService {
    @RestClient
    private lateinit var recommendClient: RecommendClient


    @Inject
    private lateinit var repoRepository: RepoRepository

    @Inject
    private lateinit var topicRepository: TopicRepository

    @Inject
    private lateinit var userRepository: UserRepository


    fun getPopular(n: Int, offset: Int): List<RepositoryDto>? {
        val items = recommendClient.getPopular(n, offset)
        return if (items.isNotEmpty()) {
            repoRepository.findByIds(items.map { it.id.toLong() }).map {
                RepositoryDto.fromRepository(it)
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

    fun getLatest(n: Int, offset: Int): List<RepositoryDto>? {
        val items = recommendClient.getLatest(n, offset)
        return if (items.isNotEmpty()) {
            repoRepository.findByIds(items.map { it.id.toLong() })
                .map { RepositoryDto.fromRepository(it) }
        } else null
    }

    fun getLatestTopics(n: Int, offset: Int): List<Topic>? {
        val items = recommendClient.getLatestByCategory(n, offset, "topic")
        return if (items.isNotEmpty()) {
            topicRepository.findByIds(items.map { it.id.toLong() })
        } else null
    }

    fun getItemNeighbors(n: Int, offset: Int, itemId: String): List<RepositoryDto>? {
        val items = recommendClient.getItemNeighbors(n, offset, itemId)
        return if (items.isNotEmpty()) {
            repoRepository.findByIds(items.map { it.id.toLong() })
                .map { RepositoryDto.fromRepository(it) }
        } else null
    }

    fun getUserNeighbors(n: Int, offset: Int, userId: String): List<User>? {
        val items = recommendClient.getUserNeighbors(n, offset, userId)
        return if (items.isNotEmpty()) {
            userRepository.findByIds(items.map { it.id.toLong() })
        } else null
    }
}