package com.elintefaz.El_intefaz.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="ProductsUsed")
@NoArgsConstructor
@AllArgsConstructor
public class ProductUsed {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_Product_Used")
    private Integer idProductUsed;


    @ManyToOne
    @Cascade(CascadeType.REFRESH)
    @JoinColumn( name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    private Products product;

    @Column(name = "amount")
    private Integer amount;

    @Column(name="pay")
    private Double pay;


    @ManyToOne
    @Cascade(CascadeType.REFRESH)
    @JoinColumn( name = "Order_ID", referencedColumnName = "Order_ID")
    private Order nOrder;
}
