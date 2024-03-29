package com.example.salesmanagment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class ProductDto {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "availableQuantity is required")
    private Integer availableQuantity;

    @NotNull(message = "price is required")
    private Double price;

    @NotNull(message = "categoryId is required")
    private Long categoryId;

    @NotNull(message = "sellerId is required")
    private Long sellerId;
}
