package repository;

import model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Page<Brand> findByBrandNameContains(String name, Pageable pager);
    List<Brand> findByBrandNameContains(String name);
    List<Brand> findByBrandNameContains(String name, Sort sort);
}
