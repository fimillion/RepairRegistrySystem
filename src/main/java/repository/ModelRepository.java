package repository;

import model.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ModelRepository extends JpaRepository<Model, Long> {

        Page<model.Model>findByModelNameContains(String name,Pageable pager);
        List<model.Model>findByModelNameContains(String name);
        List<model.Model>findByModelNameContains(String name,Sort sort);
}