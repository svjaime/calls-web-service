package com.svjaime.callswebservice.api;

import com.svjaime.callswebservice.domain.entity.Call;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

/**
 * Implementation of {@link CallResource}
 */
@ApplicationScoped
public class CallResourceImpl implements CallResource {

    @Override
    public Uni<List<Call>> getAll() {
        return Call.listAll(Sort.by("caller_number"));
    }

    @Override
    public Uni<Response> create(Call call) {
        return Panache.<Call>withTransaction(call::persist)
                .map(inserted -> Response.created(URI.create("/calls/" + inserted.id)).build());
    }

    @Override
    public Uni<Response> delete(@RestPath Long id) {
        return Panache.withTransaction(() -> Call.deleteById(id))
                .map(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build());
    }
}