package ru.arman.lidertest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arman.lidertest.dto.CityDto;
import ru.arman.lidertest.exception.CityNotFoundException;
import ru.arman.lidertest.model.City;
import ru.arman.lidertest.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    private ResponseEntity<?> addCity(@RequestBody @Valid City city) {
        return ResponseEntity.ok(cityService.addCity(city));
    }

    @PostMapping("/edit/{city_id}")
    private ResponseEntity<?> editCity(@PathVariable Long city_id,
                                       @RequestBody CityDto cityDto) throws CityNotFoundException {

        return ResponseEntity.ok(cityService.editCity(city_id, cityDto));
    }
}
