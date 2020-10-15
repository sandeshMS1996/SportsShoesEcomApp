package com.sportshoes.ecom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public final class Cart {
    private Long productId;
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return getProductId() != null ? getProductId().equals(cart.getProductId()) : cart.getProductId() == null;
    }

    @Override
    public int hashCode() {
        return getProductId() != null ? getProductId().hashCode() : 0;
    }
}
