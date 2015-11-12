package repository;

import model.Completeness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompletenessRepository  extends JpaRepository<Completeness,Long> {

    Page<Completeness> findByDirectoryNameContains(String name, Pageable pager);

    List<Completeness> findByDirectoryNameContains(String name);

    List<Completeness> findByDirectoryNameContains(String name, Sort sort);
}
