package com.svjaime.callswebservice.domain.service;

import com.svjaime.callswebservice.api.response.PagingResponse;
import com.svjaime.callswebservice.domain.entity.Call;
import com.svjaime.callswebservice.domain.entity.CallType;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Calls Service - to handle all the business logic.
 */
@ApplicationScoped
public class CallService {

    private static final Integer DEFAULT_START_INDEX = 0;
    private static final Integer DEFAULT_LAST_INDEX = 20;

    /**
     * Get all calls, filtered by type.
     *
     * @param startIndex The 'start at' index.
     * @param lastIndex  The last index of the range (inclusive).
     * @param callType   Filter by call type.
     * @return A {@link Uni} containing a {@link PagingResponse} object.
     */
    public Uni<PagingResponse> getAllCalls(final Integer startIndex, final Integer lastIndex, final String callType) {
        final Integer start = Optional.ofNullable(startIndex).orElse(DEFAULT_START_INDEX);
        final Integer last = Optional.ofNullable(lastIndex).orElse(DEFAULT_LAST_INDEX);
        final Optional<CallType> callTypeOptional = Optional.ofNullable(callType).map(CallType::fromString);

        final PanacheQuery<Call> allCallsQuery =
                callTypeOptional.isPresent()
                        ? Call.find("callType", callTypeOptional.get())
                        : Call.findAll();

        return buildPagingResponseUni(start, last, allCallsQuery);
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
     * @param id The call ID to deleteCall
     * @return @return a {@link Uni} containing a {@link Boolean} representing whether or not the deletion was successfull.
     */
    public Uni<Boolean> deleteById(@RestPath final Long id) {
        return Panache.withTransaction(() -> Call.deleteById(id));
    }

    private Uni<PagingResponse> buildPagingResponseUni(final Integer startIndex, final Integer lastIndex,
                                                       final PanacheQuery<Call> query) {

        final Uni<Long> itemCount = query.count();
        final Uni<List<Call>> itemList = query.range(startIndex, lastIndex).list()
                .onFailure().recoverWithItem(Collections.emptyList());

        return Uni.combine().all().unis(itemCount, itemList).combinedWith(unis ->
                PagingResponse.builder()
                        .startIndex(startIndex)
                        .lastIndex(lastIndex)
                        .total((Long) unis.get(0))
                        .items((List<Call>) unis.get(1))
                        .build());
    }

}
