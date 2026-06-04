package com.pfe.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor
@NoArgsConstructor
@Entity
// Annonce
public class Announcement {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate publicationDate;
    private LocalDate applicationDeadLine;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String technologies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id", nullable = false)
    private User recruiter;

}
