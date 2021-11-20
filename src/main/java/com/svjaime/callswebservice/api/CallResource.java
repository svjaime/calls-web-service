package com.svjaime.callswebservice.api;

import com.svjaime.callswebservice.domain.entity.Call;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@Path("/calls")
@ApplicationScoped
public class CallResource {

    @GET
    public Uni<List<Call>> getAll() {
        return Call.listAll(Sort.by("caller_number"));
    }

    @POST
    public Uni<Response> create(Call call) {
        return Panache.<Call>withTransaction(call::persist)
                .map(inserted -> Response.created(URI.create("/calls/" + inserted.id)).build());
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@RestPath final Long id) {
        return Panache.withTransaction(() -> Call.deleteById(id))
                .map(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build());
    }
}