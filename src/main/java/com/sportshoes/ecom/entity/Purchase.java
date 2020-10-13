package com.sportshoes.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseId;

    @OneToOne
    @NotNull
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Customers customer;

    @OneToMany
    private List<Products> product =  new ArrayList<>();

    private int quantity;

    @Nullable
    Long totalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    Date purchaseDate = new Date();

    public Purchase(Customers customer, List<Products> product, int quantity, Long totalPrice) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
