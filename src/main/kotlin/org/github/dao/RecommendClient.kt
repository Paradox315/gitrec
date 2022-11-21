package org.github.dao

import io.smallrye.mutiny.Uni
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.github.entity.Score
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam

@RegisterRestClient(configKey = "recommend-api")
interface RecommendClient {
    @GET
    @Path("/popular")
    fun getPopular(
        @QueryParam("n") n: Int,
        @QueryParam("offset") offset: Int
    ): List<Score>

    @GET
    @Path("/popular")
    fun getPopularAsync(
        @QueryParam("n") n: Int,
        @QueryParam("offset") offset: Int
    ): Uni<List<Score>>

    @GET
    @Path("/popular/{category}")
    fun getPopularByCategory(
        @QueryParam("n") n: Int,
        @QueryParam("offset") offset: Int,
        @PathParam("category") category: String
    ): List<Score>

    @GET
    @Path("/latest")
    fun getLatest(
        @QueryParam("n") n: Int,
        @QueryParam("offset") offset: Int
    ): List<Score>

    @GET
    @Path("/latest/{category}")
    fun getLatestByCategory(
        @QueryParam("n") n: Int,
        @QueryParam("offset") offset: Int,
        @PathParam("category") category: String
    ): List<Score>

    @GET
    @Path("/item/{item-id}/neighbors")
    fun getItemNeighbors(
        @QueryParam("n") n: Int,
        @QueryParam("offset") offset: Int,
        @PathParam("item-id") itemId: String
    ): List<Score>

    @GET
    @Path("/user/{user-id}/neighbors")
    fun getUserNeighbors(
        @QueryParam("n") n: Int,
        @QueryParam("offset") offset: Int,
        @PathParam("user-id") userId: String
    ): List<Score>

    @GET
    @Path("/recommend/{user-id}")
    fun getRecommendations(
        @QueryParam("n") n: Int,
        @QueryParam("offset") offset: Int,
        @QueryParam("write-back-type") writeBackType: String,
        @QueryParam("write-back-delay") writeBackDelay: String,
        @PathParam("user-id") userId: String
    ): List<String>

    @GET
    @Path("/recommend/{user-id}/{category}")
    fun getRecommendationsByCategory(
        @QueryParam("n") n: Int,
        @QueryParam("offset") offset: Int,
        @QueryParam("write-back-type") writeBackType: String,
        @QueryParam("write-back-delay") writeBackDelay: String,
        @PathParam("user-id") userId: String,
        @PathParam("category") category: String
    ): List<String>

}