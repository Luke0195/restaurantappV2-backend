package br.com.waiterapp.application.repositories;

import br.com.waiterapp.application.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query(value="SELECT cat FROM Category as cat where cat.name =:name")
    Optional<Category> findCategoriesByName(String name);

    @Query(value = "SELECT category FROM Category as category  where category.icon =:icon")
    Optional<Category> findByIcon(String icon);

}
