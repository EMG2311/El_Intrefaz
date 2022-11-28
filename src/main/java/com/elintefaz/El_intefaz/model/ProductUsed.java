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
    private Integer ProductUsed;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Products product;
    @Column(name = "amount")
    private Integer amount;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Order nOrder;
}
