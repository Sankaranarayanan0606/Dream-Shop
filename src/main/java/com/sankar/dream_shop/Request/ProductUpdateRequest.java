package com.sankar.dream_shop.Request;

import com.sankar.dream_shop.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductUpdateRequest {

    private long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;

    private String description;
    private Category category;

}
