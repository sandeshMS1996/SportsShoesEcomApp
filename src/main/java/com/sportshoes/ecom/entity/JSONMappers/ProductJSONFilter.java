package com.sportshoes.ecom.entity.JSONMappers;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.sportshoes.ecom.entity.Category;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@JsonFilter("ProudctFilter")
public class ProductJSONFilter {
    private Long ID;

    private String name;

    private int price;

    private LocalDateTime dateAdded;

    private Category categoryId;

}
