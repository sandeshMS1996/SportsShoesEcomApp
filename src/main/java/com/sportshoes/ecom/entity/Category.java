package com.sportshoes.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @Size(min = 5, max = 20, message = "size should be 5-20 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    Customers admin;

    public Category(long ID) {

        this.ID = ID;
    }
}
