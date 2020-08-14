package com.example.demo.configuration;

import com.example.demo.model.Order;
import com.example.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;

@Configuration
public class ModelsProcessorsConfiguration {

    private final RepositoryEntityLinks repositoryEntityLinks;

    @Autowired
    public ModelsProcessorsConfiguration(RepositoryEntityLinks repositoryEntityLinks) {
        this.repositoryEntityLinks = repositoryEntityLinks;
    }

    @Bean
    RepresentationModelProcessor<EntityModel<Order>> orderProcessor() {
        return new RepresentationModelProcessor<EntityModel<Order>>() {
            @Override
            public EntityModel<Order> process(EntityModel<Order> resource) {
                resource.add(repositoryEntityLinks.linkToCollectionResource(ProductsRepository.class));
                return resource;
            }
        };
    }

}
