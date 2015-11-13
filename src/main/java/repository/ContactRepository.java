package repository;

import model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
        Page<Contact> findByContactTypeContains(String name, Pageable pager);
        List<Contact> findByContactTypeContains(String name);
        List<Contact> findByContactTypeContains(String name, Sort sort);
    }
