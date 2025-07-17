package com.groupe4.bibliotheque.controller;

import com.groupe4.bibliotheque.dto.LivreRequestDto;
import com.groupe4.bibliotheque.model.Auteur;
import com.groupe4.bibliotheque.model.Livre;
import com.groupe4.bibliotheque.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    @Autowired
    private LivreService livreService;

    @PostMapping
    public ResponseEntity<Livre> createLivre(@RequestBody LivreRequestDto livre) {
        Livre savedLivre = livreService.createLivre(livre);
        return ResponseEntity.ok(savedLivre);
    }

    @GetMapping
    public ResponseEntity<List<Livre>> getAllLivres() {
        List<Livre> livres = livreService.getAllLivres();
        return ResponseEntity.ok(livres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Integer id) {
        Optional<Livre> livre = livreService.getLivreById(id);
        return livre.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Integer id, @RequestBody Livre livreDetails) {
        Livre updatedLivre = livreService.updateLivre(id, livreDetails);
        if (updatedLivre != null) {
            return ResponseEntity.ok(updatedLivre);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Integer id) {
        livreService.deleteLivre(id);
        return ResponseEntity.noContent().build();
    }
}