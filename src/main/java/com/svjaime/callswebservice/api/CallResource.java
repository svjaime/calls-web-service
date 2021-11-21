package com.svjaime.callswebservice.api;

import com.svjaime.callswebservice.api.response.PagingResponse;
import com.svjaime.callswebservice.domain.entity.Call;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

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
     * @param startIndex The 'start at' index (inclusive).
     * @param lastIndex  The last index of the range (inclusive).
     * @param callType   Filter by call type.
     * @return A {@link Uni} containing a {@link PagingResponse} object.
     */
    @GET
    @Path("all")
    Uni<PagingResponse> getAll(@RestQuery final Integer startIndex,
                               @RestQuery final Integer lastIndex,
                               @RestQuery final String callType);

    /**
     * Create a single Call.
     *
     * @param call The {@link Call} object to persist.
     * @return A {@link Uni} containing a {@link Response} object.
     */
    @POST
    Uni<Response> createSingleCall(final Call call);

    /**
     * Create multiple Calls.
     *
     * @param calls The {@link List} of {@link Call} objects to persist.
     * @return A {@link Uni} containing a {@link Response} object.
     */
    @POST
    @Path("create")
    Uni<Response> createMultipleCalls(final List<Call> calls);

    /**
     * Delete a call by ID.
     *
     * @param id The call ID to deleteCall
     * @return @return a {@link Uni} containing a {@link Response} object.
     */
    @DELETE
    @Path("{id}")
    Uni<Response> deleteCall(@RestPath final Long id);

}
