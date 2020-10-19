package com.sportshoes.ecom.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Where(clause = "deletion_flag = false")
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

    @CreationTimestamp
    private LocalDateTime dateAdded;

    @Column(name = "deletion_flag", nullable = false)
    private boolean deletedFlag;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category categoryId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Customers Admin;

    public Products(Long adminID, Long categoryId, String name, int price) {
        this.Admin = new Customers(adminID);
        this.categoryId = new Category(categoryId);
        this.name = name;
        this.price = price;
    }

    public Products(Long categoryId, String name, int price) {
        this.categoryId = new Category(categoryId);
        this.name = name;
        this.price = price;
    }



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

    @PrePersist
    public void setDeletionFlag() {
        this.deletedFlag = false;

    }
}
