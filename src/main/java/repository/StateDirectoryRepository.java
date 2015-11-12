package repository;


import model.StateDirectory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateDirectoryRepository extends JpaRepository<StateDirectory,Long> {
    Page<StateDirectory> findByDirectoryNameContains(String name, Pageable pager);

    List<StateDirectory> findByDirectoryNameContains(String name);

    List<StateDirectory> findByDirectoryNameContains(String name, Sort sort);
}
