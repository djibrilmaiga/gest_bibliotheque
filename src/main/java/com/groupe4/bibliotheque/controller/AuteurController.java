package com.groupe4.bibliotheque.controller;

import com.groupe4.bibliotheque.model.Auteur;
import com.groupe4.bibliotheque.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {

    @Autowired
    private AuteurService auteurService;

    @PostMapping
    public ResponseEntity<Auteur> createAuteur(@RequestBody Auteur auteur) {
        Auteur savedAuteur = auteurService.createAuteur(auteur);
        return ResponseEntity.ok(savedAuteur);
    }

    @GetMapping
    public ResponseEntity<List<Auteur>> getAllAuteurs() {
        List<Auteur> auteurs = auteurService.getAllAuteurs();
        return ResponseEntity.ok(auteurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auteur> getAuteurById(@PathVariable Integer id) {
        Optional<Auteur> auteur = auteurService.getAuteurById(id);
        return auteur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auteur> updateAuteur(@PathVariable Integer id, @RequestBody Auteur auteurDetails) {
        Auteur updatedAuteur = auteurService.updateAuteur(id, auteurDetails);
        if (updatedAuteur != null) {
            return ResponseEntity.ok(updatedAuteur);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuteur(@PathVariable Integer id) {
        auteurService.deleteAuteur(id);
        return ResponseEntity.noContent().build();
    }
}