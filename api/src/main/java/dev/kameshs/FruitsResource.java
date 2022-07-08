package dev.kameshs;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitsResource {

  @ConfigProperty(name = "my.fruit", defaultValue = "apple")
  String defaultFruit;

  @GET
  @Path("/fruits/default")
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
  @Path("/fruits/{season}")
  public Response fruitsBySeason(@PathParam("season") String season) {
    return Response
        .ok(Fruit.fruitsBySeason(season))
        .build();
  }

  @POST
  @Path("/fruits/add")
  @Transactional
  public Response addFruit(Fruit fruit) {
    fruit.persist();
    return Response
        .accepted()
        .build();
  }

  @DELETE
  @Path("/fruits/{id}")
  @Transactional
  public Response deleteFruit(@PathParam("id") long id) {
    Fruit fruit = Fruit.findById(id);

    if (fruit == null) {
      Response
          .status(404, String.format("Fruit does not exist"))
          .build();
    }
    fruit.delete();
    return Response
        .noContent()
        .build();
  }
}