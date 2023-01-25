package ru.arman.lidertest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arman.lidertest.dto.SightDto;
import ru.arman.lidertest.exception.CityNotFoundException;
import ru.arman.lidertest.exception.SightNotFoundException;
import ru.arman.lidertest.model.Sight;
import ru.arman.lidertest.model.SightType;
import ru.arman.lidertest.service.SightService;

import java.util.Optional;

@RestController
@RequestMapping("/sight")
public class SightController {
    private final SightService sightService;

    @Autowired
    public SightController(SightService sightService) {
        this.sightService = sightService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSights(@RequestParam Optional<Boolean> sortByName,
                                          @RequestParam Optional<SightType> sightType) {
        return ResponseEntity.ok(sightService.getAllSights(sortByName, sightType));
    }

    @GetMapping("/all/{city_id}")
    public ResponseEntity<?> getAllSightsOfCity(@PathVariable Long city_id) {
        return ResponseEntity.ok(sightService.getAllByCityId(city_id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSight(@RequestBody @Valid Sight sight,
                                      @RequestParam Long city_id) throws CityNotFoundException {
        return ResponseEntity.ok(sightService.addSight(sight, city_id));
    }

    @PostMapping("/edit/{sight_id}")
    public ResponseEntity<?> editSight(@PathVariable Long sight_id,
                                       @RequestBody SightDto sightDto) throws SightNotFoundException {
       return ResponseEntity.ok(sightService.editSight(sight_id, sightDto));
    }

    @DeleteMapping("/delete/{sight_id}")
    public void deleteSight(@PathVariable Long sight_id) throws SightNotFoundException {
        sightService.deleteSight(sight_id);
    }
}
