package com.svjaime.callswebservice.domain.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    public String callerNumber;

    /**
     * Callee Number: the phone number of the callee.
     */
    @Column
    public String calleeNumber;

    /**
     * Start Timestamp: start timestamp of the call.
     */
    @Column
    public Date startTimestamp;

    /**
     * End Timestamp: end timestamp of the call.
     */
    @Column
    public Date endTimestamp;

    /**
     * Call Type: Inbound or Outbound
     */
    @Column
    public CallType callType;

}
