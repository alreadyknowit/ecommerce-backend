package com.example.ecommercebackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDto {


    private int id;
    @NotBlank(message = "Product name must be inserted")
    private String productName;
    @NotBlank(message = "Quantity must be inserted")
    private String quantityPerUnit;
    @NotNull(message = "Unit price must be inserted")
    @Min(value = 0, message = "unit price cannot be less than 0")
    private Double unitPrice;
    @NotNull(message = "Units in stock must be inserted")
    @Min(value = 0, message = "Units in stock cannot be less than 0")
    private Integer unitsInStock;
    private int categoryId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
