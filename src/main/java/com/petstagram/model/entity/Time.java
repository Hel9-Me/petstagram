package com.petstagram.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Time {
    @CreatedDate
    @Column(updatable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    private Timestamp updatedAt;
}
