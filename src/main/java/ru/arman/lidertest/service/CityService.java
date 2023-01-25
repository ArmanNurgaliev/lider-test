package ru.arman.lidertest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.arman.lidertest.dto.CityDto;
import ru.arman.lidertest.exception.CityNotFoundException;
import ru.arman.lidertest.model.City;
import ru.arman.lidertest.repository.CityRepository;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public City editCity(Long cityId, CityDto cityDto) throws CityNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(String.format("City with id %s not found", cityId)));

        if (cityDto.getPopulation() != null)
            city.setPopulation(cityDto.getPopulation());
        if (cityDto.getIsMetro() != null)
            city.setIsMetro(cityDto.getIsMetro());

        return cityRepository.save(city);
    }
}
