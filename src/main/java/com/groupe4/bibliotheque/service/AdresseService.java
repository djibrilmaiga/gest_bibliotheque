package com.groupe4.bibliotheque.service;

import com.groupe4.bibliotheque.dao.AdresseDAO;
import com.groupe4.bibliotheque.model.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    @Autowired
    private AdresseDAO adresseDAO;

    public Adresse createAdresse(Adresse adresse) {
        return adresseDAO.save(adresse);
    }

    public List<Adresse> getAllAdresses() {
        return adresseDAO.findAll();
    }

    public Optional<Adresse> getAdresseById(Integer id) {
        return adresseDAO.findById(id);
    }

    public Adresse updateAdresse(Integer id, Adresse adresseDetails) {
        Optional<Adresse> adresseOptional = adresseDAO.findById(id);
        if (adresseOptional.isPresent()) {
            Adresse adresse = adresseOptional.get();
            adresse.setQuartier(adresseDetails.getQuartier());
            adresse.setPorte(adresseDetails.getPorte());
            return adresseDAO.save(adresse);
        }
        return null;
    }

    public void deleteAdresse(Integer id) {
        adresseDAO.deleteById(id);
    }
}