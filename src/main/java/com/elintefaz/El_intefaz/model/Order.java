package com.elintefaz.El_intefaz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.CodePointLength;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@Table(name="Order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "Order_ID")
    private Integer idOrder;
    @OneToMany(mappedBy = "productId")
    @Column( name = "Products_id", nullable = false )
    private List<Products> products;
    @Column(nullable = false, name = "email")
    private String email;
    @Column(nullable = false,name = "N_cel")
    private String nCel;
    @Column(nullable = false,name="Star_Date")
    private Date starDate;
    @Column(nullable = true,name = "End_Date")
    private Date endDate;
    @Column(nullable = false, name = "Address")
    private String address;








}
