package ru.arman.lidertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arman.lidertest.model.Sight;

import java.util.List;

@Repository
public interface SightRepository extends JpaRepository<Sight, Long> {
    List<Sight> findByCity_id(Long cityId);
}
