package com.groupe4.bibliotheque.dao;

import com.groupe4.bibliotheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreDAO extends JpaRepository<Livre, Integer> {
}
