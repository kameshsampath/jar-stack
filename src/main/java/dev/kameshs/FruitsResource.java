package dev.kameshs;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitsResource {

    @ConfigProperty(name = "my.fruit", defaultValue = "apple")
    String defaultFruit;

    @GET
    @Path("/default")
    public Response defaultFruit() {
        return Response
            .ok(defaultFruit)
            .build();
    }

    @GET
    @Path("/fruits")
    public Response fruits() {
        return Response
                .ok(Fruit.listAll())
                .build();
    }

    @GET
    @Path("/fruit/{season}")
    public Response fruitsBySeason(@PathParam("season") String season) {
        return Response
                .ok(Fruit.fruitsBySeason(season))
                .build();
    }

    @POST
    @Path("/fruit/add")
    @Transactional
    public Response addFruit(Fruit fruit) {
        fruit.persist();
        return Response
                .accepted()
                .build();
    }
}