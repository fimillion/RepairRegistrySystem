package repository;

import model.Malfunctions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MalfunctionRepository extends JpaRepository<Malfunctions,Long>{
    Page<Malfunctions> findByDirectoryNameContains(String name, Pageable pager);
    List<Malfunctions> findByDirectoryNameContains(String name);
    List<Malfunctions> findByDirectoryNameContains(String name,Sort sort);
}
