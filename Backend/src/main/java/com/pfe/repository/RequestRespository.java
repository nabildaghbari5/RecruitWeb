package com.pfe.repository;

import java.time.LocalDate;
import java.util.List;

import com.pfe.model.Request;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestRespository  extends JpaRepository<Request, Integer>{
     @Query("SELECT d FROM Request d WHERE d.status=:status")
	 List<Request> findByStatus(@Param("status") String status);

    @Query("SELECT r FROM Request r WHERE r.candidateId = :candidateId")
    List<Request> findRequestsByCandidateId(@Param("candidateId") String candidateId);


    @Query("SELECT COUNT(d) FROM Request d WHERE d.dateCreation >= :startDate")
    Long countRecentCandidatures(LocalDate startDate);

    @Query("SELECT d FROM Request d ORDER BY d.dateCreation DESC")
    List<Request> findRecentCandidatures(Pageable pageable);

    @Query("SELECT d.status, COUNT(d) FROM Request d GROUP BY d.status")
    List<Object[]> countCandidaturesByStatus();
}
