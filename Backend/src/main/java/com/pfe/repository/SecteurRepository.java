package com.pfe.repository;

import com.pfe.model.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecteurRepository  extends JpaRepository<Secteur, Integer> {
}
