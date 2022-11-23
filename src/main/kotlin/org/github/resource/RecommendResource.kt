package org.github.resource

import org.github.entity.response.ResponseInfo
import org.github.service.RecommendService
import org.github.utils.Helper
import javax.inject.Inject
import javax.ws.rs.*

@Path("/recommend")
class RecommendResource {
    @Inject
    lateinit var recommendService: RecommendService

    @GET
    @Path("/popular/{page}/{size}")
    fun getPopularRecommendations(
        @QueryParam("category") @DefaultValue("") category: String,
        @PathParam("page") page: Int,
        @PathParam("size") size: Int
    ) = ResponseInfo.success(
        Helper.getOffset(page, size).let { (n, offset) ->
            when (category) {
                "topic" -> recommendService.getPopularTopics(n, offset)
                else -> recommendService.getPopular(n, offset)
            }
        }
    )


    @GET
    @Path("/latest/{page}/{size}")
    fun getNewRecommendations(
        @QueryParam("category") @DefaultValue("") category: String,
        @PathParam("page") page: Int,
        @PathParam("size") size: Int
    ) = ResponseInfo.success(
        Helper.getOffset(page, size).let { (n, offset) ->
            when (category) {
                "topic" -> recommendService.getLatestTopics(n, offset)
                else -> recommendService.getLatest(n, offset)
            }
        }
    )

    @GET
    @Path("/item-neighbors/{page}/{size}/{itemId}")
    fun getItemNeighbors(
        @PathParam("page") page: Int,
        @PathParam("size") size: Int,
        @PathParam("itemId") itemId: String
    ) = ResponseInfo.success(
        Helper.getOffset(page, size).let { (n, offset) ->
            recommendService.getItemNeighbors(n, offset, itemId)
        }
    )

    @GET
    @Path("/user-neighbors/{page}/{size}/{userId}")
    fun getUserNeighbors(
        @PathParam("page") page: Int,
        @PathParam("size") size: Int,
        @PathParam("userId") userId: String
    ) = ResponseInfo.success(
        Helper.getOffset(page, size).let { (n, offset) ->
            recommendService.getUserNeighbors(n, offset, userId)
        }
    )

    @GET
    @Path("/")
    fun ping() = ResponseInfo.success("pong")
}