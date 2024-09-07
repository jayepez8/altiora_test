package com.altiora.tracking.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders;
}
