package com.sportshoes.ecom.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Cart {
    private Long productId;
    private String quantity;
}
