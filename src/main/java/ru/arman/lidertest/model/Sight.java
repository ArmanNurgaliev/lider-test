package ru.arman.lidertest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Sight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Sight name can't be empty")
    private String name;

    @NotNull(message = "Date of construction can't be null")
    private LocalDate dateOfConstruction;
    private String description;

    @NotNull(message = "Sight type can't be empty")
    @Enumerated(EnumType.STRING)
    private SightType type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;
}
