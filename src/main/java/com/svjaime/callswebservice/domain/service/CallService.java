package com.svjaime.callswebservice.domain.service;

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
 * Calls Service - to handle all the business logic.
 */
@ApplicationScoped
public class CallService {

    /**
     * Get all calls.
     *
     * @return A {@link Uni} containing a {@link List} of {@link Call} objects.
     */
    public Uni<List<Call>> getAllCalls() {
        return Call.listAll(Sort.by("caller_number"));
    }

    /**
     * Create a single Call.
     *
     * @param call The {@link Call} object to persist.
     * @return A {@link Uni} containing a {@link Response}.
     */
    public Uni<Response> createSingleCall(final Call call) {
        return Panache.<Call>withTransaction(call::persist)
                .map(inserted -> Response.created(URI.create("/calls/" + inserted.id)).build());
    }

    /**
     * Delete a call by ID.
     *
     * @param id The call ID to delete
     * @return @return a {@link Uni} containing a {@link Response}.
     */
    public Uni<Response> deleteById(@RestPath final Long id) {
        return Panache.withTransaction(() -> Call.deleteById(id))
                .map(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build());
    }
}
