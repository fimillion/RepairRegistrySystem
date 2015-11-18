package repository;


import model.DeviceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {

    Page<DeviceType> findByDeviceTypeNameContains(String name, Pageable pager);
    List<DeviceType> findByDeviceTypeNameContains(String name);
    List<DeviceType> findByDeviceTypeNameContains(String name, Sort sort);
}

