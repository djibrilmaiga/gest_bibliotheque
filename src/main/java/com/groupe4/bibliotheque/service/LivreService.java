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

    public Livre createLivre(LivreRequestDto livre) {
        // Initialisation de la liste des auteurs
        Set<Auteur> auteurs = new HashSet<>();

        //
        for (Integer auteur : livre.getAuteurs()) {
            auteurs.add(auteurDAO.findById(auteur)
                    .orElseThrow(() -> new EntityNotFoundException("Auteur non trouvé avec l'ID : " + auteur)));
        }
        // Récupère l'entité catégorie
        Categorie categorie = categorieDAO.findById(livre.getIdCategorie())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvé avec l'ID : " + livre.getIdCategorie()));

        // Création d'une entité livre
        Livre newLivre = new Livre();
        // Conversion des données Dto vers l'entité
        newLivre.setTitre(livre.getTitre());
        newLivre.setCategorie(categorie);
        newLivre.setAuteurs(auteurs);



        return livreDAO.save(newLivre);
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