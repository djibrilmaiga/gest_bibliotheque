package com.groupe4.bibliotheque.dao;

import com.groupe4.bibliotheque.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuteurDAO extends JpaRepository<Auteur, Integer> {
}
