package com.svjaime.callswebservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonValue;

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

}
