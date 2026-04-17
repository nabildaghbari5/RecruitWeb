package com.pfe.repository;

import com.pfe.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnnouncementRespository extends JpaRepository<Announcement, Integer>{

}
