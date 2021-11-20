package com.svjaime.callswebservice.api;

import com.svjaime.callswebservice.domain.entity.Call;
import com.svjaime.callswebservice.domain.service.CallService;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Implementation of {@link CallResource}
 */
@ApplicationScoped
public class CallResourceImpl implements CallResource {

    @Inject
    CallService callService;

    @Override
    public Uni<List<Call>> getAll() {
        return callService.getAllCalls();
    }

    @Override
    public Uni<Response> create(final Call call) {
        return callService.createSingleCall(call);
    }

    @Override
    public Uni<Response> delete(@RestPath final Long id) {
        return callService.deleteById(id);
    }
}
