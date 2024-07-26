package com.elintefaz.El_intefaz.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="Products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Products {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "NAME", unique = true)
    private String name;
    @Column(nullable = false, name = "STOCK")
    private Integer stock;

    @Column(nullable = false,name = "PRICE")
    private Double price;


    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "Id_Category")
    private Category category;





}
