package com.example.salesmanagment.entity;

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
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "creation_date")
    private Date creationDate;

    @NotNull
    @Column(name = "total_price")
    private double totalPrice;

    @NotNull
    @Column(name = "total_quantity")
    private int totalQuantity;

    @JsonIgnoreProperties({"mobileNo", "email", "address"})
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonIgnoreProperties({"mobileNo", "email", "address"})
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @JsonIgnoreProperties({"description", "availableQuantity", "creationDate"})
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
