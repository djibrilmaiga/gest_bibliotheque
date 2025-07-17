package com.groupe4.bibliotheque.dao;

import com.groupe4.bibliotheque.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseDAO extends JpaRepository<Adresse, Integer> {
}


