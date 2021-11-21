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
    public String callerNumber;

    /**
     * Callee Number: the phone number of the callee.
     */
    @Column
    @NotBlank
    @NotNull
    public String calleeNumber;

    /**
     * Start Timestamp: start timestamp of the call.
     */
    @Column
    @NotNull
    public Date startTimestamp;

    /**
     * End Timestamp: end timestamp of the call.
     */
    @Column
    @NotNull
    public Date endTimestamp;

    /**
     * Call Type: Inbound or Outbound
     */
    @Column
    @NotNull
    public CallType callType;

}
