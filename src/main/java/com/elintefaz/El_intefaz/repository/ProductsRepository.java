package com.elintefaz.El_intefaz.repository;

import com.elintefaz.El_intefaz.model.Category;
import com.elintefaz.El_intefaz.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {
    Optional<Products> findByName(String name);
    void deleteByName(String name);

    Optional<List<Products>> findByCategory(Category category);
}
