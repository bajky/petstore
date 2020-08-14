package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "PTS", name = "ORDERS")
@Getter
@Setter
public class Order extends BaseModel {
    private Double totalPrice;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_orders",
            joinColumns = {
                    @JoinColumn(name = "id_order")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id_product")
            }
    )
    private List<Product> products;
}
