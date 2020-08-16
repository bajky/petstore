package com.example.demo.controller;

import com.example.demo.controller.dto.AssetRepresentationModel;
import com.example.demo.model.ProductAsset;
import com.example.demo.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/assets")
public class ProductAssetsController {

    private final AssetsService assetsService;

    @Autowired
    public ProductAssetsController(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    @PostMapping(path = "/save")
    public void handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("productId") long productId) {
        assetsService.assignAssetToProduct(productId, file);
    }

    @GetMapping(path = "/search/{productName}")
    public AssetRepresentationModel handleGetResources(@PathVariable("productName") String productId) {
        AssetRepresentationModel assetRepresentationModel = new AssetRepresentationModel();
        assetsService.getAssetsByProductName(productId)
                .forEach(asset -> assetRepresentationModel
                        .add(linkTo(methodOn(ProductAssetsController.class).handleGetFile(asset.getId(), null)).withRel("assets")));
        return assetRepresentationModel;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> handleGetFile(@PathVariable(name = "id") long assetId,
                                                HttpServletResponse servletResponse) {
        try {
            ServletOutputStream outputStream = servletResponse.getOutputStream();
            servletResponse.setContentType(MimeTypeUtils.IMAGE_PNG_VALUE);
            Optional<ProductAsset> productAsset = assetsService.getProductAssetById(assetId);
            productAsset.ifPresent(asset -> {
                try {
                    outputStream.write(asset.getAsset());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("");
    }
}
