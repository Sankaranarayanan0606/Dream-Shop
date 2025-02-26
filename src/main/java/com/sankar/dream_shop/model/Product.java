package com.sankar.dream_shop.model;

import java.math.BigDecimal;
import java.util.List;

public class Product {

    private long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;


    private Category category;
    private List<Image> images;

}
