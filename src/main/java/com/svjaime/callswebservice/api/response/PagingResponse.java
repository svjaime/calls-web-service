package com.svjaime.callswebservice.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.svjaime.callswebservice.domain.entity.Call;

import java.util.List;
import java.util.Objects;

/**
 * Paging Response
 */
public class PagingResponse {

    @JsonProperty
    private final Integer startIndex;

    @JsonProperty
    private final Integer lastIndex;

    @JsonProperty
    private final Long total;

    @JsonProperty
    private final List<Call> items;

    private PagingResponse(final PagingResponseBuilder builder) {
        this.startIndex = builder.startIndex;
        this.lastIndex = builder.lastIndex;
        this.total = builder.total;
        this.items = builder.items;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public Integer getLastIndex() {
        return lastIndex;
    }

    public Long getTotal() {
        return total;
    }

    public List<Call> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PagingResponse that = (PagingResponse) o;
        return Objects.equals(startIndex, that.startIndex) && Objects.equals(lastIndex, that.lastIndex)
                && Objects.equals(total, that.total) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startIndex, lastIndex, total, items);
    }

    /**
     * Creates a builder for {@link PagingResponse}.
     *
     * @return A new {@link PagingResponseBuilder}.
     */
    public static PagingResponseBuilder builder() {
        return new PagingResponseBuilder();
    }

    /**
     * Builder class for {@link PagingResponse}.
     */
    public static final class PagingResponseBuilder {
        private Integer startIndex;
        private Integer lastIndex;
        private Long total;
        private List<Call> items;

        private PagingResponseBuilder() {
        }

        /**
         * Set the startIndex.
         *
         * @param startIndex The 'start at' index (inclusive).
         * @return The {@link PagingResponseBuilder}.
         */
        public PagingResponseBuilder startIndex(final Integer startIndex) {
            this.startIndex = startIndex;
            return this;
        }

        /**
         * Set the lastIndex.
         *
         * @param lastIndex The last index of the range (inclusive).
         * @return The {@link PagingResponseBuilder}.
         */
        public PagingResponseBuilder lastIndex(final Integer lastIndex) {
            this.lastIndex = lastIndex;
            return this;
        }

        /**
         * Set the total.
         *
         * @param total The total number items.
         * @return The {@link PagingResponseBuilder}.
         */
        public PagingResponseBuilder total(final Long total) {
            this.total = total;
            return this;
        }

        /**
         * Set the {@link List} of {@link Call} items.
         *
         * @param items The items' list.
         * @return The {@link PagingResponseBuilder}.
         */
        public PagingResponseBuilder items(final List<Call> items) {
            this.items = items;
            return this;
        }

        /**
         * Returns a new {@link PagingResponse} with the builder's parameters.
         *
         * @return The new {@link PagingResponse}
         */
        public PagingResponse build() {
            return new PagingResponse(this);
        }
    }
}
