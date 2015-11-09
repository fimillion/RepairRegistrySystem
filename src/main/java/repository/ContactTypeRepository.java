package repository;

import model.ContactType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
        Page<ContactType> findByContactTypeNameContains(String name, Pageable pager);
        List<ContactType> findByContactTypeNameContains(String name);
        List<ContactType> findByContactTypeNameContains(String name,Sort sort);
    }
