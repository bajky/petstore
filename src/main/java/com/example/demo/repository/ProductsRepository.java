package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductsRepository extends PagingAndSortingRepository<Product, Long> {
    @RestResource(path = "nameStartWith", rel = "nameStartWith")
    Page<Product> findByProductNameStartingWith(@Param("with")String startWith, Pageable pageable);
}
