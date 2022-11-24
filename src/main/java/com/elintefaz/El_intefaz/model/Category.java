package com.elintefaz.El_intefaz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="Category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "Id_Category")
    @GeneratedValue(strategy = IDENTITY)
    private Integer idCategory;


    @Column(name = "name")
    private String name;


}
