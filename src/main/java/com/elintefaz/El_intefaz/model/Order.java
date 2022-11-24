package com.elintefaz.El_intefaz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="Order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Order_ID")
    private Integer idOrder;



    @Column(nullable = false,name = "High_Date")
    private Date HighDate;

    @Column(nullable = false, name = "email")
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "products")
    private Products products;

    @Column(nullable = false,name = "amount")
    private Integer amount;






}
