package org.github.config

import org.github.entity.response.ResponseInfo
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ExceptionHandler : ExceptionMapper<Exception> {
    override fun toResponse(exception: Exception): Response {
        return when (
            exception
        ) {
            is IllegalArgumentException -> Response.status(Response.Status.BAD_REQUEST)
                .entity(ResponseInfo.badRequest(exception.message ?: "参数错误")).build()

            else -> Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ResponseInfo.fail(exception.message ?: "服务器错误")).build()
        }
    }
}