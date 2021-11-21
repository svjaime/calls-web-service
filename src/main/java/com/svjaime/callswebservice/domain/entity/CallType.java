package com.svjaime.callswebservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Call Types.
 */
public enum CallType {

    /**
     * Inbound call type.
     */
    INBOUND("inbound"),

    /**
     * Outbound call type.
     */
    OUTBOUND("outbound");

    private String type;

    CallType(final String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    /**
     * Get a {@link CallType} from string.
     *
     * @param type The call type.
     * @return The enum value, or null if not found.
     */
    public static CallType fromString(final String type) {
        return Stream.of(values())
                .filter(callType -> Objects.equals(callType.getType(), type.trim().toLowerCase()))
                .findFirst()
                .orElse(null);
    }

}
