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

    @Column(name = "caller_number")
    private String callerNumber;

    @Column(name = "callee_number")
    private String calleeNumber;

    @Column(name = "start_timestamp")
    private Date startTimestamp;

    @Column(name = "end_timestamp")
    private Date endTimestamp;

    @Column(name = "call_type")
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
