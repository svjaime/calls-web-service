package com.svjaime.callswebservice.domain.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Call Entity.
 */
@Entity
@Cacheable
public class Call extends PanacheEntity {

    /**
     * Caller Number: the phone number of the caller.
     */
    @Column
    @NotBlank
    @NotNull
    private String callerNumber;

    /**
     * Callee Number: the phone number of the callee.
     */
    @Column
    @NotBlank
    @NotNull
    private String calleeNumber;

    /**
     * Start Timestamp: start timestamp of the call.
     */
    @Column
    @NotNull
    private Date startTimestamp;

    /**
     * End Timestamp: end timestamp of the call.
     */
    @Column
    @NotNull
    private Date endTimestamp;

    /**
     * Call Type: Inbound or Outbound
     */
    @Column
    @NotNull
    private CallType callType;

    public String getCallerNumber() {
        return callerNumber;
    }

    public String getCalleeNumber() {
        return calleeNumber;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public Date getEndTimestamp() {
        return endTimestamp;
    }

    public CallType getCallType() {
        return callType;
    }
}
