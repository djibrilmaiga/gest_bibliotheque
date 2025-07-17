package com.groupe4.bibliotheque.service;

import com.groupe4.bibliotheque.dao.CategorieDAO;
import com.groupe4.bibliotheque.model.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    private CategorieDAO categorieDAO;

    public Categorie createCategorie(Categorie categorie) {
        return categorieDAO.save(categorie);
    }

    public List<Categorie> getAllCategories() {
        return categorieDAO.findAll();
    }

    public Optional<Categorie> getCategorieById(Integer id) {
        return categorieDAO.findById(id);
    }

    public Categorie updateCategorie(Integer id, Categorie categorieDetails) {
        Optional<Categorie> categorieOptional = categorieDAO.findById(id);
        if (categorieOptional.isPresent()) {
            Categorie categorie = categorieOptional.get();
            categorie.setNom(categorieDetails.getNom());
            return categorieDAO.save(categorie);
        }
        return null;
    }

    public void deleteCategorie(Integer id) {
        categorieDAO.deleteById(id);
    }
}