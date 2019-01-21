package com.meetup.msa.productservice.repository;

import com.meetup.msa.productservice.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cveerapaneni on 7/1/17.
 */
//@RepositoryRestResource(path = "products", collectionResourceRel = "products", itemResourceRel = "product")
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    /*
    @RestResource(exported = false)
    @Override
    void delete(Long aLong);

    @RestResource(exported = false)
    @Override
    void delete(Product product);

    @RestResource(exported = false)
    @Override
    void delete(Iterable<? extends Product> iterable);

    @RestResource(exported = false)
    @Override
    void deleteAll();
    */
}
