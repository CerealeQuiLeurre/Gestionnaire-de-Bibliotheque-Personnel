package com.github.cerealequileurre.bibliotheque.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public record BookCreationDTO(

        @NotBlank(message = "Le titre ne peut pas être vide") String titre,

        @NotBlank(message = "Le nom de l'auteur est obligatoire") String auteur,

        @Pattern(regexp = "^(?:(?=(?:\\D*\\d){13}$)(?:978|979)-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d|\\d{10}|\\d{13})$", message = "L'ISBN doit contenir exactement 10 ou 13 chiffres (ex: 978-2-07-041579-3 ou 13 chiffres bruts)") String isbn,

        LocalDate dateLecture,

        @Min(value = 0, message = "La note minimale est de 0") @Max(value = 5, message = "La note maximale est de 5") Integer note) {
}