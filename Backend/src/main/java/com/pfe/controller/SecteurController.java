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

    @PostMapping
    public ResponseEntity<Secteur> create(@RequestBody Secteur secteur) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(secteurService.create(secteur));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Secteur> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(secteurService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Secteur>> getAll() {
        return ResponseEntity.ok(secteurService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Secteur> update(@PathVariable Integer id,
                                          @RequestBody Secteur secteur) {
        return ResponseEntity.ok(secteurService.update(id, secteur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        secteurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}