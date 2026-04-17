package com.pfe.service;

import com.pfe.model.Secteur;
import com.pfe.repository.SecteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecteurServiceImpl implements SecteurService {

    private final SecteurRepository secteurRepository;

    @Override
    public Secteur create(Secteur secteur) {
        return secteurRepository.save(secteur);
    }

    @Override
    public Secteur update(Integer id, Secteur secteur) {
        Secteur existing = secteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Secteur introuvable"));

        existing.setName(secteur.getName());

        return secteurRepository.save(existing);
    }

    @Override
    public Secteur getById(Integer id) {
        return secteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Secteur introuvable"));
    }

    @Override
    public List<Secteur> getAll() {
        return secteurRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        if (!secteurRepository.existsById(id)) {
            throw new RuntimeException("Secteur introuvable");
        }
        secteurRepository.deleteById(id);
    }
}