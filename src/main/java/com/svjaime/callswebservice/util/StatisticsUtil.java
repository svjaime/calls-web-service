package com.svjaime.callswebservice.util;

import com.svjaime.callswebservice.domain.entity.Call;
import com.svjaime.callswebservice.domain.entity.CallType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility classe to handle statistics' calculations.
 */
public class StatisticsUtil {

    private static final Double FIRST_5_MINUTES_COST = 0.10;
    private static final Double PER_MINUTE_COST = 0.05;

    private StatisticsUtil() {
    }

    /**
     * Get total calls duration.
     *
     * @param calls    List of calls.
     * @param callType the call type to filter by.
     * @return Total calls duration.
     */
    public static Long getTotalCallsDuration(final List<Call> calls, final CallType callType) {
        return calls.stream()
                .filter(call -> call.getCallType() == callType)
                .map(StatisticsUtil::getSingleCallDuration)
                .mapToLong(Long::longValue).sum();
    }

    /**
     * Get total number of calls (all types).
     *
     * @param calls List of calls.
     * @return Total number of calls.
     */
    public static Long getTotalCalls(final List<Call> calls) {
        return (long) calls.size();
    }

    /**
     * Get the total number of calls by caller.
     *
     * @param calls List of calls.
     * @return A map representing the number of calls by caller.
     */
    public static Map<String, Integer> getTotalCallsByCallerMap(final List<Call> calls) {
        final Map<String, Integer> callsByCallerMap = new HashMap<>();
        calls.forEach(call -> callsByCallerMap.merge(call.getCallerNumber(), 1, (k, v) -> v++));
        return callsByCallerMap;
    }

    /**
     * Get the total number of calls by callee.
     *
     * @param calls List of calls.
     * @return A map representing the number of calls by callee.
     */
    public static Map<String, Integer> getTotalCallsByCalleeMap(final List<Call> calls) {
        final Map<String, Integer> callsByCallerMap = new HashMap<>();
        calls.forEach(call -> callsByCallerMap.merge(call.getCalleeNumber(), 1, (k, v) -> v++));
        return callsByCallerMap;
    }

    /**
     * Get the total costs from a list of Calls.
     *
     * @param calls The list of calls.
     * @return The total costs.
     */
    public static Double getTotalCosts(final List<Call> calls) {
        return calls.stream().filter(call -> call.getCallType() == CallType.OUTBOUND)
                .map(StatisticsUtil::getSingleCallCost)
                .mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Get single call duration (in seconds)
     *
     * @param call The call.
     * @return The call duration (seconds)
     */
    public static Long getSingleCallDuration(final Call call) {
        return (call.getEndTimestamp().getTime() - call.getStartTimestamp().getTime()) / 1000;
    }

    /**
     * Get single call cost (in seconds)
     *
     * @param call The call.
     * @return The call duration (seconds)
     */
    public static Double getSingleCallCost(final Call call) {
        final long duration = getSingleCallDuration(call);
        return duration < 300
                ? FIRST_5_MINUTES_COST
                : FIRST_5_MINUTES_COST + Math.ceil(((double) duration -300)/60)*PER_MINUTE_COST;
    }
}
