package org.github.resource

import io.smallrye.mutiny.Uni
import org.github.entity.response.ResponseInfo
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class ExampleResource {


    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = Uni.createFrom().item(
        "hello world"
    ).map { data -> data }


}