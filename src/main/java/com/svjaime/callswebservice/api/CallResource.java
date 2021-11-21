package com.svjaime.callswebservice.api;

import com.svjaime.callswebservice.api.request.validator.EnumValue;
import com.svjaime.callswebservice.api.response.PagingResponse;
import com.svjaime.callswebservice.api.response.StatisticsResponse;
import com.svjaime.callswebservice.domain.entity.Call;
import com.svjaime.callswebservice.domain.entity.CallType;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
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
     * @param startIndex The 'start at' index.
     * @param lastIndex  The last index of the range (inclusive).
     * @param callType   Filter by call type.
     * @return A {@link Uni} containing a {@link PagingResponse} object.
     */
    @GET
    @Path("all")
    Uni<PagingResponse> getAll(@RestQuery
                               @PositiveOrZero final Integer startIndex,

                               @RestQuery
                               @PositiveOrZero final Integer lastIndex,

                               @RestQuery
                               @EnumValue(fieldName = "type", enumClass = CallType.class) final String callType);

    /**
     * Create a single Call.
     *
     * @param call The {@link Call} object to persist.
     * @return A {@link Uni} containing a {@link Response} object.
     */
    @POST
    Uni<Response> createSingleCall(@Valid final Call call);

    /**
     * Create multiple Calls.
     *
     * @param calls The {@link List} of {@link Call} objects to persist.
     * @return A {@link Uni} containing a {@link Response} object.
     */
    @POST
    @Path("create")
    Uni<Response> createMultipleCalls(@Valid final List<Call> calls);

    /**
     * Delete a call by ID.
     *
     * @param id The call ID to deleteCall
     * @return A {@link Uni} containing a {@link Response} object.
     */
    @DELETE
    @Path("{id}")
    Uni<Response> deleteCall(@RestPath @Positive final Long id);

    /**
     * Get statistics.
     *
     * @return A {@link Uni} containing a {@link List} of {@link StatisticsResponse} objects.
     */
    @GET
    @Path("statistics")
    Uni<List<StatisticsResponse>> getStatistics();

}
