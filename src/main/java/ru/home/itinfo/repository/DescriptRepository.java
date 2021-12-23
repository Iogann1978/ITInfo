package ru.home.itinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Descript;

@Repository
public interface DescriptRepository extends JpaRepository<Descript, Long> {
}