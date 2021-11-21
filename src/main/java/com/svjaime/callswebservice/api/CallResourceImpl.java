package com.svjaime.callswebservice.api;

import com.svjaime.callswebservice.api.response.PagingResponse;
import com.svjaime.callswebservice.api.response.StatisticsResponse;
import com.svjaime.callswebservice.domain.entity.Call;
import com.svjaime.callswebservice.domain.service.CallService;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    @Inject
    CallService callService;

    @Override
    public Uni<PagingResponse> getAll(final Integer startIndex, final Integer lastIndex, final String callType) {
        return callService.getAllCalls(startIndex, lastIndex, callType);
    }

    @Override
    public Uni<Response> createSingleCall(final Call call) {
        return callService.createSingleCall(call)
                .map(inserted -> Response.created(URI.create("/calls/" + inserted.id)).build());
    }

    @Override
    public Uni<Response> createMultipleCalls(final List<Call> calls) {
        return callService.createMultipleCalls(calls)
                .map(item -> Response.ok().status(NO_CONTENT).build());
    }

    @Override
    public Uni<Response> deleteCall(final Long id) {
        return callService.deleteById(id).map(deleted -> deleted
                ? Response.ok().status(NO_CONTENT).build()
                : Response.ok().status(NOT_FOUND).build());
    }

    @Override
    public Uni<List<StatisticsResponse>> getStatistics() {
        return callService.getStatistics();
    }
}
