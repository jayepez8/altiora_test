package com.altiora.tracking.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author jyepez on 7/9/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITEMS")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "ITEM_CODE", nullable = false, unique = true)
    private String itemCode;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "UNIT_PRICE", nullable = false)
    private double unitPrice;

    @Column(name = "CREATE_DATE",nullable = false)
    private LocalDateTime createDate;

    @Column(name = "STOCK")
    private int stock;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }
}
