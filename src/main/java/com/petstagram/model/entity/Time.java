package com.petstagram.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

/**
 * 시간 관련 정보를 자동으로 처리하는 부모 클래스.
 * 이 클래스는 엔티티가 생성될 때와 수정될 때 시간을 자동으로 기록합니다.
 */
@Getter
@MappedSuperclass  // 해당 클래스를 상속받은 엔티티에서 컬럼을 상속받아 사용하도록 함.
@EntityListeners(AuditingEntityListener.class)  // Auditing 기능을 활성화하여 생성일과 수정일을 자동 관리
public class Time {

    @CreatedDate  // 엔티티가 처음 생성될 때 자동으로 생성 시간을 기록
    @Column(name = "created_at", updatable = false)  // 'updatable = false'로 설정하여 수정되지 않도록 방지
    private Timestamp createdAt;  // 생성 시간 (자동 설정)

    @Column(name = "updated_at")  // 수정된 시간
    @LastModifiedDate  // 엔티티가 수정될 때 자동으로 마지막 수정 시간을 기록
    private Timestamp updatedAt;  // 마지막 수정 시간 (자동 설정)
}
