package com.example.salesmanagment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class SaleDto {

    private Long id;

    @NotNull(message = "totalPrice is required")
    private Double totalPrice;

    @NotNull(message = "totalQuantity is required")
    private Integer totalQuantity;

    @NotNull(message = "productId is required")
    private Long productId;

    @NotNull(message = "clientId is required")
    private Long clientId;

    @NotNull(message = "sellerId is required")
    private Long sellerId;
}
