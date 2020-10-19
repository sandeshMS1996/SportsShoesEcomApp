package com.sportshoes.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    private Customers customer;

    @ElementCollection
    @CollectionTable(name = "products_quantity")
    @MapKeyJoinColumn(name = "product_id")
    Map<Products, Integer> product = new HashMap<>();

    @Nullable
    Long totalPrice;

    @CreationTimestamp
    LocalDate purchaseDate;

}
