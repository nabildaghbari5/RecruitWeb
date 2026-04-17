package com.pfe.repository;

import com.pfe.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageModelRepository extends JpaRepository<ImageModel, Integer> {

    ImageModel findByUserId(String userId);

}
