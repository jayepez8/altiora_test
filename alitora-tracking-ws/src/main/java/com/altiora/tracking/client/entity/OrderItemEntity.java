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
@Table(name = "ORDER_ITEMS")
public class OrderItemEntity {

    @EmbeddedId
    private OrderItemId id = new OrderItemId();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "ITEM_ID")
    private ItemEntity item;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private double totalPrice;

    @Column(name = "CREATE_DATE",nullable = false)
    private LocalDateTime createDate;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }
}
