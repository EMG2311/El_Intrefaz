package com.elintefaz.El_intefaz.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "ORDER_ITEMS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Products product;

    @Column(name = "QUANTITY", nullable = false)
    @Positive
    private Integer quantity;

}