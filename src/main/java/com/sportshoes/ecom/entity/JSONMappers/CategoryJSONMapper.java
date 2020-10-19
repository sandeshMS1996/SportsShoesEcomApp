package com.sportshoes.ecom.entity.JSONMappers;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryJSONMapper {
    @Size(min = 3, max = 20, message = "Category name size 3-20 chars")
    private  String name;
}
