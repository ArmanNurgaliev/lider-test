package ru.arman.lidertest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.arman.lidertest.dto.SightDto;
import ru.arman.lidertest.exception.CityNotFoundException;
import ru.arman.lidertest.exception.SightNotFoundException;
import ru.arman.lidertest.model.City;
import ru.arman.lidertest.model.Sight;
import ru.arman.lidertest.model.SightType;
import ru.arman.lidertest.repository.CityRepository;
import ru.arman.lidertest.repository.SightRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SightService {
    private final SightRepository sightRepository;
    private final CityRepository cityRepository;

    @Autowired
    public SightService(SightRepository sightRepository,
                        CityRepository cityRepository) {
        this.sightRepository = sightRepository;
        this.cityRepository = cityRepository;
    }

    public Sight addSight(Sight sight, Long city_id) throws CityNotFoundException {
        City city = cityRepository.findById(city_id)
                .orElseThrow(() -> new CityNotFoundException(String.format("City with id %s not found", city_id)));

        sight.setCity(city);
        return sightRepository.save(sight);
    }

    public Sight editSight(Long sightId, SightDto sightDto) throws SightNotFoundException {
        Sight sight = sightRepository.findById(sightId)
                .orElseThrow(() -> new SightNotFoundException(String.format("Sight with id %s not found", sightId)));

        if (sightDto.getDescription() != null && !sightDto.getDescription().equals(""))
            sight.setDescription(sightDto.getDescription());

        return sightRepository.save(sight);
    }

    public void deleteSight(Long sightId) throws SightNotFoundException {
        Sight sight = sightRepository.findById(sightId)
                .orElseThrow(() -> new SightNotFoundException(String.format("Sight with id %s not found", sightId)));

        sightRepository.delete(sight);
    }

    public List<Sight> getAllByCityId(Long cityId) {
        return sightRepository.findByCity_id(cityId);
    }

    public List<Sight> getAllSights(Optional<Boolean> sortByName, Optional<SightType> sightType) {
        if (sightType.isPresent() && sortByName.isPresent() && sortByName.get())
            return sightRepository.findByType(sightType.get(), Sort.by("name"));

        if (sortByName.isPresent() && sortByName.get())
            return sightRepository.findAll(Sort.by("name"));

        if (sightType.isPresent())
            return sightRepository.findByType(sightType.get());

        return sightRepository.findAll();
    }
}
