package com.example.demo.repository;

import com.example.demo.model.BaseModel;
import com.example.demo.model.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "PTS", name = "ORDERS")
public class Order extends BaseModel {
    private double total_price;
    private Date time;

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

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
