package com.groupe4.bibliotheque.dao;

import com.groupe4.bibliotheque.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieDAO extends JpaRepository<Categorie, Integer> {
}
