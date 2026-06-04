package com.pfe.controller;

import com.pfe.model.Secteur;
import com.pfe.service.SecteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/secteur")
@RequiredArgsConstructor
public class SecteurController {

    private final SecteurService secteurService;

    @PostMapping("/{recruteurId}")
    public ResponseEntity<Secteur> create(@PathVariable Integer recruteurId ,  @RequestBody Secteur secteur) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(secteurService.create(recruteurId , secteur));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Secteur> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(secteurService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Secteur>> getAll() {
        return ResponseEntity.ok(secteurService.getAll());
    }

    @PutMapping("/{id}/{recruteurId}")
    public ResponseEntity<Secteur> update(@PathVariable Integer id, @PathVariable Integer recruteurId ,
                                          @RequestBody Secteur secteur) {
        return ResponseEntity.ok(secteurService.update(id , recruteurId, secteur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        secteurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}