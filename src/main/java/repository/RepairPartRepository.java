package repository;

import model.RepairPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairPartRepository extends JpaRepository<RepairPart,Long> {
}
