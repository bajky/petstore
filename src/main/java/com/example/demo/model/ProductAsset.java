package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(schema = "PTS", name = "PRODUCT_ASSETS")
@Getter
@Setter
public class ProductAssets extends BaseModel {
    @Lob(type="BLOB")
    private byte[] asset;
}