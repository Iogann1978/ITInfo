package ru.home.itinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.itinfo.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
