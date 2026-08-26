package com.github.cerealequileurre.bibliotheque.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record BookCreationDTO(

                @NotBlank(message = "Le titre ne peut pas être vide") String titre,

                @NotBlank(message = "Le nom de l'auteur est obligatoire") String auteur,

                String isbn,

                LocalDate dateLecture,

                @Min(value = 0, message = "La note minimale est de 0") @Max(value = 5, message = "La note maximale est de 5") Integer note) {

}