package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UsersRepository extends PagingAndSortingRepository<User, Long> {
    @RestResource(exported = false)
    User findByUserName(@Param("userName") String userName);

    @Override
    @RestResource(exported = false)
    <S extends User> Iterable<S> saveAll(Iterable<S> iterable);
}
