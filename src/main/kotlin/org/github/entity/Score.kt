package org.github.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class Score(
    @JsonProperty("Id") val id: String,
    @JsonProperty("Score") val score: Int
)
