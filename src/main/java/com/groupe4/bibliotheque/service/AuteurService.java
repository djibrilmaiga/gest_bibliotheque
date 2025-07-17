package com.groupe4.bibliotheque.service;

import com.groupe4.bibliotheque.dao.AuteurDAO;
import com.groupe4.bibliotheque.model.Auteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuteurService {

    @Autowired
    private AuteurDAO auteurDAO;

    public Auteur createAuteur(Auteur auteur) {
        return auteurDAO.save(auteur);
    }

    public List<Auteur> getAllAuteurs() {
        return auteurDAO.findAll();
    }

    public Optional<Auteur> getAuteurById(Integer id) {
        return auteurDAO.findById(id);
    }

    public Auteur updateAuteur(Integer id, Auteur auteurDetails) {
        Optional<Auteur> auteurOptional = auteurDAO.findById(id);
        if (auteurOptional.isPresent()) {
            Auteur auteur = auteurOptional.get();
            auteur.setNom(auteurDetails.getNom());
            auteur.setPrenom(auteurDetails.getPrenom());
            auteur.setAdresse(auteurDetails.getAdresse());
            auteur.setLivres(auteurDetails.getLivres());
            return auteurDAO.save(auteur);
        }
        return null;
    }

    public void deleteAuteur(Integer id) {
        auteurDAO.deleteById(id);
    }
}