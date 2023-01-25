package ru.arman.lidertest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "City name can't be empty")
    private String name;

    @Range(message = "Population can't be 0")
    @NotNull(message = "City population can't be empty")
    private Long population;

    private Boolean isMetro = false;

    @NotBlank(message = "Country name can't be empty")
    private String country;
}
