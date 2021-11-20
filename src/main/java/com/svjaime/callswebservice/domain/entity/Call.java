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

    @Column
    public String callerNumber;

    @Column
    public String calleeNumber;

    @Column
    public Date startTimestamp;

    @Column
    public Date endTimestamp;

    @Column
    public CallType callType;

}
