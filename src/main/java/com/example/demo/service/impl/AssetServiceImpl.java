package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.model.ProductAsset;
import com.example.demo.repository.AssetsRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetsService {
    private ProductsRepository productsRepository;
    private AssetsRepository assetsRepository;

    @Autowired
    public AssetServiceImpl(ProductsRepository productsRepository, AssetsRepository assetsRepository) {
        this.productsRepository = productsRepository;
        this.assetsRepository = assetsRepository;
    }

    @Override
    @Transactional
    public void assignAssetToProduct(long productId, MultipartFile multipartFile) {
        try {
            Optional<Product> productOptional = productsRepository.findById(productId);
            final ProductAsset productAsset = new ProductAsset();
            productAsset.setAsset(multipartFile.getBytes());
            productOptional.ifPresent(productAsset::setProduct);
            assetsRepository.save(productAsset);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductAsset> getAssetsByProductName(String productName) {
        return assetsRepository.findAllByProductProductName(productName);
    }

    public Optional<ProductAsset> getProductAssetById(long id) {
        return assetsRepository.findById(id);
    }
}
