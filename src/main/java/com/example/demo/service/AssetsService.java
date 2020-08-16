package com.example.demo.service;

import com.example.demo.model.ProductAsset;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface AssetsService {
    void assignAssetToProduct(long productId, MultipartFile multipartFile);
    List<ProductAsset> getAssetsByProductName(String productName);
    Optional<ProductAsset> getProductAssetById(long id);
}
