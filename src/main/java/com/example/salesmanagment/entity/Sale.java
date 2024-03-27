package com.example.salesmanagment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Table(name = "sale")
@Getter
@Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
