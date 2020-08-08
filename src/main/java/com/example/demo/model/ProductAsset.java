package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "PTS", name = "PRODUCT_ASSETS")
@Getter
@Setter
public class ProductAsset extends BaseModel {
    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] asset;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}