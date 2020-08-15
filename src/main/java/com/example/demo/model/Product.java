package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "PTS", name = "PRODUCT")
@Getter
@Setter
public class Product extends BaseModel {
    @NotBlank
    private String productName;
    private double price;
    private String description;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductAsset> assets = new ArrayList<>();
}
