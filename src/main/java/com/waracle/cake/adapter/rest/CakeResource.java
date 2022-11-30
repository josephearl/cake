package com.waracle.cake.adapter.rest;

import com.waracle.cake.CakeManager;
import java.net.URI;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/cakes")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class CakeResource {
  private final CakeManager manager;

  @GET
  @APIResponses({
    @APIResponse(
        responseCode = "200",
        content =
            @Content(
                schema = @Schema(implementation = CakeResponse.class, type = SchemaType.ARRAY)))
  })
  public Response readCakes() {
    var response = manager.readCakes().stream().map(CakeResponse::from).toList();
    return Response.ok(response).build();
  }

  @POST
  @Transactional
  @APIResponses({
    @APIResponse(
        responseCode = "201",
        content = @Content(schema = @Schema(implementation = CakeResponse.class))),
    @APIResponse(responseCode = "400")
  })
  public Response createCake(@Valid CakeRequest request) {
    var response = CakeResponse.from(manager.createCake(request.toCakeDetails()));
    return Response.created(URI.create("./cakes/" + response.id())).entity(response).build();
  }

  @DELETE
  @Transactional
  @APIResponses({@APIResponse(responseCode = "200")})
  public Response deleteCakes() {
    manager.deleteCakes();
    return Response.ok().build();
  }

  @GET
  @Path("/{id}")
  @APIResponses({
    @APIResponse(
        responseCode = "200",
        content = @Content(schema = @Schema(implementation = CakeResponse.class))),
    @APIResponse(responseCode = "404")
  })
  public Response readCake(@PathParam("id") long id) {
    var response =
        manager.readCake(id).map(CakeResponse::from).orElseThrow(() -> cakeNotFoundException(id));
    return Response.ok(response).build();
  }

  @PUT
  @Transactional
  @Path("/{id}")
  @APIResponses({
    @APIResponse(
        responseCode = "200",
        content = @Content(schema = @Schema(implementation = CakeResponse.class))),
    @APIResponse(responseCode = "400"),
    @APIResponse(responseCode = "404")
  })
  public Response updateCake(@PathParam("id") long id, @Valid CakeRequest request) {
    var response =
        manager
            .updateCake(id, request.toCakeDetails())
            .map(CakeResponse::from)
            .orElseThrow(() -> cakeNotFoundException(id));
    return Response.ok(response).build();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  @APIResponses({@APIResponse(responseCode = "200"), @APIResponse(responseCode = "404")})
  public Response deleteCake(@PathParam("id") long id) {
    if (!manager.deleteCake(id)) throw cakeNotFoundException(id);
    return Response.ok().build();
  }

  private static NotFoundException cakeNotFoundException(long id) {
    return new NotFoundException("cake " + id + " not found");
  }
}
