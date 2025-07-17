package com.groupe4.bibliotheque.controller;

import com.groupe4.bibliotheque.model.Adresse;
import com.groupe4.bibliotheque.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adresses")
public class AdresseController {

    @Autowired
    private AdresseService adresseService;

    @PostMapping
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse adresse) {
        Adresse savedAdresse = adresseService.createAdresse(adresse);
        return ResponseEntity.ok(savedAdresse);
    }

    @GetMapping
    public ResponseEntity<List<Adresse>> getAllAdresses() {
        List<Adresse> adresses = adresseService.getAllAdresses();
        return ResponseEntity.ok(adresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable Integer id) {
        Optional<Adresse> adresse = adresseService.getAdresseById(id);
        return adresse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable Integer id, @RequestBody Adresse adresseDetails) {
        Adresse updatedAdresse = adresseService.updateAdresse(id, adresseDetails);
        if (updatedAdresse != null) {
            return ResponseEntity.ok(updatedAdresse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Integer id) {
        adresseService.deleteAdresse(id);
        return ResponseEntity.noContent().build();
    }
}