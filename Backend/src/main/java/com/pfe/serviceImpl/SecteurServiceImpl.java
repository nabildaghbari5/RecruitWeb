package com.pfe.serviceImpl;

import com.pfe.model.Secteur;
import com.pfe.model.User;
import com.pfe.repository.SecteurRepository;
import com.pfe.repository.UserRepository;
import com.pfe.service.SecteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecteurServiceImpl implements SecteurService {

    private final SecteurRepository secteurRepository;
    private final UserRepository userRepository ;

    @Override
    public Secteur create(Integer recruteurId , Secteur secteur) {
        User recruteur = userRepository.findById(recruteurId)
                .orElseThrow();
        secteur.setRecruteur(recruteur);
        return secteurRepository.save(secteur);
    }

    @Override
    public Secteur update(Integer id ,  Integer recruteurId , Secteur secteur) {
        Secteur existing = secteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Secteur introuvable"));
        User recruteur = userRepository.findById(recruteurId)
                .orElseThrow();
        existing.setRecruteur(recruteur);
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