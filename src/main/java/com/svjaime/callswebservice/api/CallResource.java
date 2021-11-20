package com.svjaime.callswebservice.api;

import com.svjaime.callswebservice.domain.entity.Call;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API entry points for the Calls Web Service (REST).
 */
@Path("/calls")
@Produces("application/json")
@Consumes("application/json")
public interface CallResource {

    /**
     * Get all calls.
     *
     * @return A {@link Uni} containing a {@link List} of {@link Call} objects.
     */
    @GET
    Uni<List<Call>> getAll();

    /**
     * Create a single Call.
     *
     * @param call The {@link Call} object to persist.
     * @return A {@link Uni} containing a {@link Response}.
     */
    @POST
    Uni<Response> create(final Call call);

    /**
     * Delete a call by ID.
     *
     * @param id The call ID to delete
     * @return @return a {@link Uni} containing a {@link Response}.
     */
    @DELETE
    @Path("{id}")
    Uni<Response> delete(@RestPath final Long id);

}
