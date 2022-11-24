package com.elintefaz.El_intefaz.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="Products")
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "NAME")
    private String name;
    @Column(nullable = false, name = "STOCK")
    private Integer Stock;

    @Column(nullable = false,name = "PRICE")
    private Double price;

    @Column(nullable = false,name = "HIGH_DATE")
    private Date highDate;


    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "CATEGORY")
    private Category category;



}
