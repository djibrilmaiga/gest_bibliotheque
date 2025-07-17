package com.groupe4.bibliotheque.dto;

import lombok.Data;

import java.util.List;

@Data
public class LivreRequestDto {
    private String titre;
    private int idCategorie;
    private List<Integer> auteurs;

}
