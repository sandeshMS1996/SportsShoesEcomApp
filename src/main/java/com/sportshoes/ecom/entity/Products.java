package com.sportshoes.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    @NotNull
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "date_added")
    @Temporal(TemporalType.DATE)
    private Date dateAdded = new Date();

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category categoryId;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    Customers Admin;

    public Products(Long ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products products = (Products) o;

        return getID() != null ? getID().equals(products.getID()) : products.getID() == null;
    }

    @Override
    public int hashCode() {
        return getID() != null ? getID().hashCode() : 0;
    }
}
