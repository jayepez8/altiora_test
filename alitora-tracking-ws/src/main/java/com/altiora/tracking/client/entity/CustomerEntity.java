package com.altiora.tracking.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author jyepez on 7/9/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMERS")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "IDENTIFICATION",nullable = false, unique = true)
    private String identification;

    @Column(name = "FIRST_NAME",nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME",nullable = false)
    private String lastName;

    @Column(name = "CREATE_DATE",nullable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }
}
