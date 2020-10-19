package com.sportshoes.ecom.entity.JSONMappers;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.sportshoes.ecom.entity.Category;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class ProductJSONFilter {

    private final String name;

    private final int price;

    private final Long categoryId;

}
