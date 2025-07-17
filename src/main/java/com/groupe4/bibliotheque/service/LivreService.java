package com.groupe4.bibliotheque.service;

import com.groupe4.bibliotheque.dao.AuteurDAO;
import com.groupe4.bibliotheque.dao.CategorieDAO;
import com.groupe4.bibliotheque.dao.LivreDAO;
import com.groupe4.bibliotheque.dto.LivreRequestDto;
import com.groupe4.bibliotheque.model.Auteur;
import com.groupe4.bibliotheque.model.Categorie;
import com.groupe4.bibliotheque.model.Livre;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LivreService {

    @Autowired
    private LivreDAO livreDAO;
    @Autowired
    private AuteurDAO auteurDAO;
    @Autowired
    private CategorieDAO categorieDAO;

    public Livre createLivre(LivreRequestDto livreDto) {
        // Créer un objet Livre
        Livre nouveauLivre = new Livre();

        // Affectet le titre
        nouveauLivre.setTitre(livreDto.getTitre());

        // Récupère l'objet catégorie
        Categorie cat = categorieDAO.findById(livreDto.getIdCategorie())
                .orElseThrow(() -> new EntityNotFoundException("Catégorie introuvable"));
        // Affecter l'objet catégorie
        nouveauLivre.setCategorie(cat);

        // Récuperer les auteurs dans la Bd
        Set<Auteur> auteurs = new HashSet<>();

        for(Integer idAutreur : livreDto.getAuteurs()){

            auteurs.add(auteurDAO.findById(idAutreur)
                    .orElseThrow(
                            () -> new EntityNotFoundException("Auteur introuvable")
                    ));
        }
        // Affectation des auteurs
        nouveauLivre.setAuteurs(auteurs);

        // Affecter l'objet livre dans chaque auteurs
        auteurs.forEach( auteur -> auteur.getLivres().add(nouveauLivre));

        return livreDAO.save(nouveauLivre);
    }

    public List<Livre> getAllLivres() {
        return livreDAO.findAll();
    }

    public Optional<Livre> getLivreById(Integer id) {
        return livreDAO.findById(id);
    }

    public Livre updateLivre(Integer id, Livre livreDetails) {
        Optional<Livre> livreOptional = livreDAO.findById(id);
        if (livreOptional.isPresent()) {
            Livre livre = livreOptional.get();
            livre.setTitre(livreDetails.getTitre());
            livre.setCategorie(livreDetails.getCategorie());
            livre.setAuteurs(livreDetails.getAuteurs());
            return livreDAO.save(livre);
        }
        return null;
    }

    public void deleteLivre(Integer id) {
        livreDAO.deleteById(id);
    }
}