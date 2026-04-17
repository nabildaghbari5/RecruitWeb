package com.pfe.service;
import com.pfe.model.Secteur;

import java.util.List;

public interface SecteurService {
    Secteur create(Secteur secteurDTO);
    Secteur update(Integer id, Secteur secteur);
    Secteur getById(Integer id);
    List<Secteur> getAll();
    void delete(Integer id);
}
