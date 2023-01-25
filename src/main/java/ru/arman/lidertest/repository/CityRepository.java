package ru.arman.lidertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arman.lidertest.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
