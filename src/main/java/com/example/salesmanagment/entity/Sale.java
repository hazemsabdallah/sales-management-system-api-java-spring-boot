package com.example.salesmanagment.entity;

import com.example.salesmanagment.dto.ProductDto;
import com.example.salesmanagment.dto.SaleDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "sale")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_date")
    private Date creationDate;

    @NotNull(message = "totalPrice is required")
    @Column(name = "total_price")
    private Double totalPrice;

    @NotNull(message = "totalQuantity is required")
    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @JsonIgnoreProperties({"mobileNo", "email", "address"})
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonIgnoreProperties({"mobileNo", "email", "address"})
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @JsonIgnoreProperties({"description", "availableQuantity", "creationDate"})
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "product_id")
    private Product product;

    public Sale() {
    }

    public Sale(SaleDto saleDto) {
        this.id = saleDto.getId();
        this.totalPrice = saleDto.getTotalPrice();
        this. totalQuantity = saleDto.getTotalQuantity();
    }
}
