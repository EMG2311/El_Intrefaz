package com.elintefaz.El_intefaz.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.CodePointLength;

import javax.persistence.*;
import javax.validation.constraints.Positive;

import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@Table(name="Order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter@Setter
public class Order {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ORDER_ID")
    private Integer idOrder;
    @Column(nullable = false, name = "EMAIL")
    private String email;
    @Column(nullable = false,name = "N_CEL")
    private String nCel;
    @Column(name="START_DATE",nullable = false)
    private Date starDate;
    @Column(nullable = true,name = "END_DATE")
    private Date endDate;
    @Column(nullable = false, name = "ADDRESS")
    private String address;
    @Column(nullable = false , name = "TOTAL")
    @Positive
    private Double total;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public List<OrderItem> getOrderItems(){
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> newList){this.orderItems=newList;};






}
