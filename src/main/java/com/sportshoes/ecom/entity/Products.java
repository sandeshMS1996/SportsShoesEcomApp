package com.sportshoes.ecom.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    private BigDecimal price;

    @Column(name = "date_added")
    @Temporal(TemporalType.DATE)
    private Date dateAdded = new Date();

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category categoryId;


}
