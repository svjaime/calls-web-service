package com.svjaime.callswebservice.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.svjaime.callswebservice.domain.entity.Call;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Statistics Response
 */
public class StatisticsResponse {

    @JsonProperty
    private LocalDate date;

    @JsonProperty
    private Long inboundCallsDuration;

    @JsonProperty
    private Long outboundCallsDuration;

    @JsonProperty
    private Long totalCalls;

    @JsonProperty
    private Double totalCost;

    @JsonProperty
    private Map<String, Integer> callsByCallerMap;

    @JsonProperty
    private Map<String, Integer> callsByCalleeMap;

    private StatisticsResponse(final StatisticsResponseBuilder builder) {
        date = builder.date;
        inboundCallsDuration = builder.inboundCallsDuration;
        outboundCallsDuration = builder.outboundCallsDuration;
        totalCalls = builder.totalCalls;
        totalCost = builder.totalCost;
        callsByCallerMap = builder.callsByCallerMap;
        callsByCalleeMap = builder.callsByCalleeMap;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getInboundCallsDuration() {
        return inboundCallsDuration;
    }

    public Long getOutboundCallsDuration() {
        return outboundCallsDuration;
    }

    public Long getTotalCalls() {
        return totalCalls;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public Map<String, Integer> getCallsByCallerMap() {
        return callsByCallerMap;
    }

    public Map<String, Integer> getCallsByCalleeMap() {
        return callsByCalleeMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatisticsResponse that = (StatisticsResponse) o;
        return Objects.equals(date, that.date) && Objects.equals(inboundCallsDuration, that.inboundCallsDuration)
                && Objects.equals(outboundCallsDuration, that.outboundCallsDuration)
                && Objects.equals(totalCalls, that.totalCalls)
                && Objects.equals(totalCost, that.totalCost)
                && Objects.equals(callsByCallerMap, that.callsByCallerMap)
                && Objects.equals(callsByCalleeMap, that.callsByCalleeMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, inboundCallsDuration, outboundCallsDuration,
                totalCalls, totalCost, callsByCallerMap, callsByCalleeMap);
    }

    /**
     * Creates a builder for {@link StatisticsResponse}.
     *
     * @return A new {@link StatisticsResponseBuilder}
     */
    public static StatisticsResponseBuilder builder() {
        return new StatisticsResponseBuilder();
    }

    /**
     * Builder class for {@link StatisticsResponse}.
     */
    public static final class StatisticsResponseBuilder {

        private LocalDate date;
        private Long inboundCallsDuration;
        private Long outboundCallsDuration;
        private Long totalCalls;
        private Double totalCost;
        private Map<String, Integer> callsByCallerMap;
        private Map<String, Integer> callsByCalleeMap;

        private StatisticsResponseBuilder() {
        }

        /**
         * Set the date.
         *
         * @param date The date.
         * @return The {@link StatisticsResponseBuilder}.
         */
        public StatisticsResponseBuilder date(final LocalDate date) {
            this.date = date;
            return this;
        }

        /**
         * Set the inbound calls total duration.
         *
         * @param inboundCallsDuration The total duration of inbound calls.
         * @return The {@link StatisticsResponseBuilder}.
         */
        public StatisticsResponseBuilder inboundCallsDuration(final Long inboundCallsDuration) {
            this.inboundCallsDuration = inboundCallsDuration;
            return this;
        }

        /**
         * Set the outbound calls total duration.
         *
         * @param outboundCallsDuration The total duration of outbound calls.
         * @return The {@link StatisticsResponseBuilder}.
         */
        public StatisticsResponseBuilder outboundCallsDuration(final Long outboundCallsDuration) {
            this.outboundCallsDuration = outboundCallsDuration;
            return this;
        }

        /**
         * Set the total number of calls.
         *
         * @param totalCalls The total number of calls.
         * @return The {@link StatisticsResponseBuilder}.
         */
        public StatisticsResponseBuilder totalCalls(final Long totalCalls) {
            this.totalCalls = totalCalls;
            return this;
        }

        /**
         * Set the total cost of all calls.
         *
         * @param totalCost the total cost.
         * @return The {@link StatisticsResponseBuilder}.
         */
        public StatisticsResponseBuilder totalCost(final Double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        /**
         * Set a Map containing all {@link Call} for each caller.
         *
         * @param callsByCallerMap The calls by caller map.
         * @return The {@link StatisticsResponseBuilder}.
         */
        public StatisticsResponseBuilder callsByCallerMap(final Map<String, Integer> callsByCallerMap) {
            this.callsByCallerMap = callsByCallerMap;
            return this;
        }

        /**
         * Set a Map containing all {@link Call} for each callee.
         *
         * @param callsByCalleeMap The calls by callee map.
         * @return The {@link StatisticsResponseBuilder}.
         */
        public StatisticsResponseBuilder callsByCalleeMap(final Map<String, Integer> callsByCalleeMap) {
            this.callsByCalleeMap = callsByCalleeMap;
            return this;
        }

        /**
         * Returns a new {@link StatisticsResponse} with the builder's parameters.
         *
         * @return The new {@link StatisticsResponse}.
         */
        public StatisticsResponse build() {
            return new StatisticsResponse(this);
        }
    }
}
