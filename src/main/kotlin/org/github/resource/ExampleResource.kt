package org.github.resource

import io.smallrye.mutiny.Uni
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.github.dao.RecommendClient
import org.github.entity.ResponseInfo
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/")
class ExampleResource {


    @GET
    @Path("hello")
    fun hello() = Uni.createFrom().item(
        "hello world"
    ).map { data -> ResponseInfo.success(data) }


}