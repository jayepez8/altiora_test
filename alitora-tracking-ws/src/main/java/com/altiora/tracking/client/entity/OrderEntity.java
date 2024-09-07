package com.altiora.tracking.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author jyepez on 7/9/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "ORDER_CODE", nullable = false, unique = true)
    private String orderCode;

    @Column(name = "ORDER_DATE", nullable = false)
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems;
}
