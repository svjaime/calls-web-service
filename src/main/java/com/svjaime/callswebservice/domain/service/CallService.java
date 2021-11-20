package com.svjaime.callswebservice.domain.service;

import com.svjaime.callswebservice.domain.entity.Call;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

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
        return Call.listAll(Sort.by("callerNumber"));
    }

    /**
     * Create a single Call.
     *
     * @param call The {@link Call} object to persist.
     * @return A {@link Uni} containing the persisted {@link Call} entity.
     */
    public Uni<Call> createSingleCall(final Call call) {
        return Panache.withTransaction(call::persist);
    }

    /**
     * Persist multiple Calls.
     *
     * @param calls The {@link List} of {@link Call} objects to persist.
     */
    public Uni<Void> createMultipleCalls(final List<Call> calls) {
        return Panache.withTransaction(() -> Call.persist(calls));
    }

    /**
     * Delete a call by ID.
     *
     * @param id The call ID to delete
     * @return @return a {@link Uni} containing a {@link Boolean} representing whether or not the deletion was successfull.
     */
    public Uni<Boolean> deleteById(@RestPath final Long id) {
        return Panache.withTransaction(() -> Call.deleteById(id));
    }
}
