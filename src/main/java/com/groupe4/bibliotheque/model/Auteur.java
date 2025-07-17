package com.groupe4.bibliotheque.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auteur")
    private int id;

    @Column(length = 50)
    private String nom;

    @Column(length = 50)
    private String prenom;

    @OneToOne
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "auteur_livre",
            joinColumns = @JoinColumn(name = "id_auteur"),
            inverseJoinColumns = @JoinColumn(name = "id_livre")
    )
    private Set<Livre> livres = new HashSet<>();
}
