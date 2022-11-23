package org.github

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import org.hamcrest.CoreMatchers.containsString

@QuarkusTest
class RecommendResourceTest {

    @Test
    fun getPopularRecommendations() {
        given()
            .`when`().get("/recommend/popular/1/5")
            .then()
            .statusCode(200)
            .body(
                containsString("成功")
            )
    }

    @Test
    fun getNewRecommendations() {
        given()
            .`when`().get("/recommend/popular/1/5")
            .then()
            .statusCode(200)
            .body(
                containsString("成功")
            )
    }

    @Test
    fun getItemNeighbors() {
        given()
            .`when`().get("/recommend/popular/1/5")
            .then()
            .statusCode(200)
            .body(
                containsString("成功")
            )
    }

    @Test
    fun getUserNeighbors() {
        given()
            .`when`().get("/recommend/popular/1/5")
            .then()
            .statusCode(200)
            .body(
                containsString("成功")
            )
    }

}

